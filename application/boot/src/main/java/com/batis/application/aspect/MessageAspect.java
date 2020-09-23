package com.batis.application.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class MessageAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(MessageAspect.class);

    @After("com.batis.application.aspect.point.CommonPointcuts.onMessageSendOrReceive()")
    public void onMessageSend(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        LOGGER.debug("{}：'{}'", methodSignature.getName().toUpperCase(), Arrays.toString(args));
    }
}
