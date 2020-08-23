package com.batis.application.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
public class CacheAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(CacheAspect.class);

    @Autowired
    RedisTemplate<String, String> redisTemplate;

//    @AfterReturning(pointcut = "com.batis.application.aspect.ponit.CommonJointPoint.onCache()&&@annotation(caching)",
//            returning = "retVal")
//    public void setCaches(JoinPoint joinPoint, Caching caching, Object retVal) {
//        Arrays.stream(caching.cacheable()).forEach(it -> System.out.println(Arrays.toString(it.cacheNames()) + ":" + it.key() + ":" + it.sync())
//        );
//    }

    @AfterReturning(pointcut ="com.batis.application.aspect.ponit.CommonJointPoint.onCache()&&" +
            "@target(cacheConfig)",
            returning = "retVal")
    public void setCache(JoinPoint joinPoint,CacheConfig cacheConfig, Object retVal) {
        Arrays.stream(cacheConfig.cacheNames()).forEach(it -> redisTemplate.opsForValue().set(it, "345"));
        LOGGER.info("----------------------------------------");
        LOGGER.info(Arrays.toString(joinPoint.getSignature().getDeclaringType().getTypeParameters()));
        LOGGER.info(Arrays.toString(joinPoint.getSignature().getDeclaringType().getGenericInterfaces()));
        LOGGER.info(Arrays.toString(joinPoint.getArgs()));
        LOGGER.info(joinPoint.getSignature().toLongString());
        LOGGER.info(joinPoint.getTarget().getClass().getName());
        LOGGER.info("After repository bean");
    }
}
