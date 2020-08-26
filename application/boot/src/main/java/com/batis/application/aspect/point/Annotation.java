package com.batis.application.aspect.point;

import org.aspectj.lang.annotation.Pointcut;

public class Annotation {

    @Pointcut("@annotation(org.springframework.cache.annotation.Caching)")
    public void caching() {
    }
}
