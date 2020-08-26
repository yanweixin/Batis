package com.batis.application.aspect.point;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Execution {
    @Pointcut("execution(public !void org.springframework.data.repository.Repository+.*(..))")
    public void repositoryMethod() {
    }

    @Pointcut("execution(public !void com.batis.application.database.repository.jpa..*(..))")
    public void jpaRepositoryMethod() {
    }
}
