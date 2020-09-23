package com.batis.application.aspect.point;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonPointcuts {

    @Pointcut("(Target.atController()||Target.atRestController()) && Within.application()")
    public void onWebRequest() {
    }

    @Pointcut("Within.service() && Bean.repository()")
    public void onRepositoryBean() {
    }

    @Pointcut("Within.service() && Target.atCacheConfig()")
    public void onCache() {
    }

    @Pointcut("Within.service() && Annotation.caching()")
    public void onCaching() {
    }

    @Pointcut("Within.service() && Within.notFileService()")
    public void onServiceCache() {
    }

    @Pointcut("Within.utilsMq()&&(Execution.utilsMqSendMethod()||Execution.utilsMqReceiveMethod())")
    public void onMessageSendOrReceive() {
    }
}
