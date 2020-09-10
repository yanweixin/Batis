package com.batis.application.aspect;

import com.batis.library.StringUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;


@Component
@Aspect
public class CacheAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(CacheAspect.class);
    private final static ObjectMapper mapper = new ObjectMapper();
    private final static Map<Integer, RedisSerializer<Object>> serializerMap;

    static {
        serializerMap = new HashMap<>();
        serializerMap.put(0, new GenericJackson2JsonRedisSerializer());
        serializerMap.put(1, new GenericJackson2JsonRedisSerializer(new ObjectMapper().enable(DeserializationFeature.USE_LONG_FOR_INTS)));
        serializerMap.put(2, new JdkSerializationRedisSerializer());
    }

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    @Value("${spring.cache.redis.time-to-live:60000}")
    private Long cacheLiveTime;
    private int sequence = 0; // A sign to set RedisSerializer with different configuration;

//    @AfterReturning(pointcut = "com.batis.application.aspect.point.CommonJointPoint.onCache()&&@annotation(caching)",
//            returning = "retVal")
//    public void setCaches(JoinPoint joinPoint, Caching caching, Object retVal) {
//        Arrays.stream(caching.cacheable()).forEach(it -> System.out.println(Arrays.toString(it.cacheNames()) + ":" + it.key() + ":" + it.sync())
//        );
//    }

//    @AfterReturning(pointcut ="com.batis.application.aspect.point.CommonJointPoint.onCache()&&" +
//            "@target(cacheConfig)",
//            returning = "retVal")
//    public void setCache(JoinPoint joinPoint,CacheConfig cacheConfig, Object retVal) {
//        Arrays.stream(cacheConfig.cacheNames()).forEach(it -> redisTemplate.opsForValue().set(it, retVal));
//        LOGGER.info("After repository bean");
//    }

    /**
     * Method 2: used in onJpaRepositoryMethod():
     * <blockquote><pre>
     * Object value = redisTemplate.opsForValue().get(key);
     * if (value == null) {
     *     Object retVal = proceedingJoinPoint.proceed(args);
     *     if (retVal instanceof Optional) {
     *         value = ((Optional) retVal).orElse("null");
     *     } else {
     *         value = retVal;
     *     }
     *     redisTemplate.opsForValue().set(key, value);
     * }
     * if (methodSignature.getReturnType() == Optional.class) {
     *     result = Optional.ofNullable("null".equals(value) ? null : value);
     * } else {
     *     result = "null".equals(value) ? null : value;
     * }
     * </pre></blockquote>
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("com.batis.application.aspect.point.CommonJointPoint.onServiceCache()")
    public Object onJpaRepositoryAction(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        LOGGER.info("\n" + "Declaring Type: " + methodSignature.getDeclaringTypeName() + "\n" +
                "Return Type: " + methodSignature.getReturnType().getName() + "\n" +
                "Name: " + methodSignature.getName() + "\n" +
                "ParamterNames: " + Arrays.toString(methodSignature.getParameterNames()) + "\n");
        String methodName = methodSignature.getName();
        String declareTypeName = methodSignature.getDeclaringType().getSimpleName();

        String keyPrefix = declareTypeName.substring(0, declareTypeName.indexOf("Service")).toLowerCase();
        String keyName = args == null ? methodName : methodName + mapper.writeValueAsString(args);
        String key = keyPrefix + ":" + keyName;
        Object result;
        if (StringUtils.startWith(methodName, Arrays.asList("find", "get", "count"))) {
            // Check the return type to use different serialization strategy
            Class<?> returnType = methodSignature.getReturnType();
            if (returnType == Long.class) {
                this.setSerializer(1);
            } else if (Serializable.class.isAssignableFrom(returnType)) {
                this.setSerializer(0);
            } else {
                this.setSerializer(2);
            }
            // Method 1: used in inServicePackage()
            result = redisTemplate.opsForValue().get(key);
            if (result == null) {
                result = proceedingJoinPoint.proceed(args);
                redisTemplate.opsForValue().set(key, result == null ? "" : result);
            } else {
                result = "".equals(result) ? null : result;
            }
        } else if (StringUtils.startWith(methodName, Arrays.asList("save", "delete", "update"))) {
            result = proceedingJoinPoint.proceed(args);
            deleteKeyByPatterns(Arrays.asList(keyPrefix + ":*"));
        } else {
            result = proceedingJoinPoint.proceed(args);
        }
        return result;
    }

    private void deleteKeyByPatterns(List<String> patterns) {
        patterns.forEach(pattern -> Objects.requireNonNull(redisTemplate.keys(pattern)).forEach(key -> redisTemplate.delete(key)));
    }

    private synchronized void setSerializer(int sequence) {
        if (this.sequence != sequence) {
            this.sequence = sequence;
            {
                LOGGER.warn("Set valueSerializer to :" + sequence);
                redisTemplate.setValueSerializer(serializerMap.get(sequence));
            }
        }
    }
}