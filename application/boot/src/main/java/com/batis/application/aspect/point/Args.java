package com.batis.application.aspect.point;

import org.aspectj.lang.annotation.Pointcut;

public class Args {

    @Pointcut("args(java.io.Serializable)")
    public void serializable() {
    }

    @Pointcut("args(java.lang.String)")
    public void string() {
    }

    @Pointcut("@args(org.springframework.web.bind.annotation.RequestParam)")
    public void atRequestParam() {
    }
}
