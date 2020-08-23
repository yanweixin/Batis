package com.batis.application.aspect.ponit;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonJointPoint {

    @Pointcut("within(com.batis.application.service..*)")
    public void inServicePackage() {
    }

    @Pointcut("inServicePackage() && bean(*Repository)")
    public void onRepositoryBean() {
    }

    @Pointcut("execution(public !void org.springframework.data.repository.Repository+.*(..))")
    public void onRepositoryMethod() {
    }

    @Pointcut("execution(public !void com.batis.application.database.repository.jpa..*(..))")
    public void onJpaRepositoryMethod() {
    }

    @Pointcut("inServicePackage() && @target(org.springframework.cache.annotation.CacheConfig)")
    public void onCache() {
    }

    @Pointcut("inServicePackage() && @annotation(org.springframework.cache.annotation.Caching)")
    public void onCaching() {
    }
}
