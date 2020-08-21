package com.batis.application.utils.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class IdListKeyGenerator implements KeyGenerator {
    private final static Logger LOGGER = LoggerFactory.getLogger(IdListKeyGenerator.class);
    @Override
    public Object generate(Object target, Method method, Object... params) {
        LOGGER.info(target.getClass().getName());
        LOGGER.info(method.getName());
        LOGGER.info(params[0].getClass().getGenericSuperclass().getTypeName());
        LOGGER.info(Arrays.toString(params[0].getClass().getGenericInterfaces()));
        return new SimpleKey("admin","root");
    }
}
