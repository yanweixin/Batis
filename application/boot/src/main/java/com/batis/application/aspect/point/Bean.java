package com.batis.application.aspect.point;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Bean {
    @Pointcut("bean(*Repository)")
    public void repository() {
    }

    @Pointcut("bean(*Controller)")
    public void controller() {
    }
}
