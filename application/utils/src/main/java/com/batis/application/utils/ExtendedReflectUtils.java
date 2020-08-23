package com.batis.application.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Arrays;

public class ExtendedReflectUtils extends com.batis.library.reflect.ReflectUtils {

    @SuppressWarnings("unchecked")
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        return Arrays.stream(beanWrapper.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(it -> beanWrapper.getPropertyValue(it) == null)
                .toArray(String[]::new);
    }
}
