package com.batis.application.aspect.point;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Target {

    @Pointcut("@target(org.springframework.stereotype.Controller)")
    public void atController() {
    }

    @Pointcut("@target(org.springframework.web.bind.annotation.RestController)")
    public void atRestController() {
    }

    @Pointcut("@target(org.springframework.cache.annotation.CacheConfig)")
    public void atCacheConfig() {
    }
}
