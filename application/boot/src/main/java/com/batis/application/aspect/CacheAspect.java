package com.batis.application.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;


@Component
@Aspect
public class CacheAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(CacheAspect.class);
    private final static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

//    @AfterReturning(pointcut = "com.batis.application.aspect.ponit.CommonJointPoint.onCache()&&@annotation(caching)",
//            returning = "retVal")
//    public void setCaches(JoinPoint joinPoint, Caching caching, Object retVal) {
//        Arrays.stream(caching.cacheable()).forEach(it -> System.out.println(Arrays.toString(it.cacheNames()) + ":" + it.key() + ":" + it.sync())
//        );
//    }

//    @AfterReturning(pointcut ="com.batis.application.aspect.ponit.CommonJointPoint.onCache()&&" +
//            "@target(cacheConfig)",
//            returning = "retVal")
//    public void setCache(JoinPoint joinPoint,CacheConfig cacheConfig, Object retVal) {
//        Arrays.stream(cacheConfig.cacheNames()).forEach(it -> redisTemplate.opsForValue().set(it, retVal));
//        LOGGER.info("After repository bean");
//    }

    @Around("com.batis.application.aspect.ponit.CommonJointPoint.onJpaRepositoryMethod()")
    public Object onJpaRepositoryAction(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//        LOGGER.info("\n" + "Declaring Type: " + methodSignature.getDeclaringTypeName() + "\n" +
//                "Return Type: " + methodSignature.getReturnType().getName() + "\n" +
//                "Name: " + methodSignature.getName() + "\n" +
//                "ParamterNames: " + Arrays.toString(methodSignature.getParameterNames()) + "\n");
        String methodName = methodSignature.getName();
        String declareTypeName = methodSignature.getDeclaringType().getSimpleName();

        String cacheName = declareTypeName.substring(0, declareTypeName.length() - 6).toLowerCase();
        String keyName = args == null ? methodName : methodName + mapper.writeValueAsString(args);

        Object result = redisTemplate.opsForValue().get(cacheName + ":" + keyName);
        if (result == null) {
            result = proceedingJoinPoint.proceed(args);
            redisTemplate.opsForValue().set(cacheName + ":" + keyName, result, 1, TimeUnit.HOURS);
        }
        return result;
    }
}
