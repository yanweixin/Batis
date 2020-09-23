package com.batis.application.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class WebAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(WebAspect.class);

    @Before("com.batis.application.aspect.point.CommonPointcuts.onWebRequest()")
    public void onWebRequest(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("\n" + "Declaring Type: " + methodSignature.getDeclaringTypeName() + "\n" +
                    "Return Type: " + methodSignature.getReturnType().getName() + "\n" +
                    "Name: " + methodSignature.getName() + "\n" +
                    "ParamterNames: " + Arrays.toString(methodSignature.getParameterNames()) + "\n");
        }
    }

}
