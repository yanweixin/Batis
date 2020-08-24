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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Component
@Aspect
public class CacheAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(CacheAspect.class);
    private final static ObjectMapper mapper = new ObjectMapper();
    private final static GenericJackson2JsonRedisSerializer intToLongDeserializer =
            new GenericJackson2JsonRedisSerializer(new ObjectMapper().enable(DeserializationFeature.USE_LONG_FOR_INTS));

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

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
    @Around("com.batis.application.aspect.point.CommonJointPoint.inServicePackage()")
    public Object onJpaRepositoryAction(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//        LOGGER.info("\n" + "Declaring Type: " + methodSignature.getDeclaringTypeName() + "\n" +
//                "Return Type: " + methodSignature.getReturnType().getName() + "\n" +
//                "Name: " + methodSignature.getName() + "\n" +
//                "ParamterNames: " + Arrays.toString(methodSignature.getParameterNames()) + "\n");
        String methodName = methodSignature.getName();
        String declareTypeName = methodSignature.getDeclaringType().getSimpleName();

        String keyPrefix = declareTypeName.substring(0, declareTypeName.length() - 11).toLowerCase();
        String keyName = args == null ? methodName : methodName + mapper.writeValueAsString(args);
        String key = keyPrefix + ":" + keyName;
        Object result;
        if (StringUtils.startWith(methodName, Arrays.asList("find", "get", "count"))) {
            if (methodSignature.getReturnType() == Long.class) {
                redisTemplate.setValueSerializer(intToLongDeserializer);
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

    public void deleteKeyByPatterns(List<String> patterns) {
        patterns.forEach(pattern -> Objects.requireNonNull(redisTemplate.keys(pattern)).forEach(key -> redisTemplate.delete(key)));
    }
}