package com.batis.application.aspect.point;

import org.aspectj.lang.annotation.Pointcut;

public class Within {

    @Pointcut("within(com.batis.application..*)")
    public void application() {
    }

    @Pointcut("within(com.batis.application.service.impl..*)")
    public void service() {
    }

    @Pointcut("!within(com.batis.application.service.impl.file..*)")
    public void notFileService() {
    }
}
