package com.batis.application.aspect.point;

import org.aspectj.lang.annotation.Pointcut;

public class Within {

    @Pointcut("within(com.batis.application..*)")
    public void application() {
    }

    @Pointcut("within(com.batis.application.service..*)")
    public void service() {
    }

    @Pointcut("!within(com.batis.application.service.system.File*)")
    public void notFileService() {
    }

    @Pointcut("within(com.batis.application.utils.mq..*)")
    public void utilsMq(){}
}
