package com.batis.application.aspect;

import com.batis.library.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MessageAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(MessageAspect.class);

    @After("com.batis.application.aspect.point.CommonPointcuts.onMessageSendOrReceive()")
    public void onMessageSendOrReceive(JoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        for (Object param : args) {
//            LOGGER.debug("{}",param);
            LOGGER.debug("{}：'{}'", methodSignature.getName().toUpperCase(), decodeMessage(param));
        }
    }

    private String decodeMessage(final Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof Message) {
            Message message = (Message) obj;
            return StringUtils.decodeBytes(message.getBody(), message.getMessageProperties().getContentEncoding());
        } else if (obj instanceof byte[]) {
            return new String((byte[]) obj);
        } else {
            return null;
        }
    }
}
