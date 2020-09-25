package com.batis.application.web;

import com.batis.library.annotation.ApiVersion;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    private final String contextPath;
    private final String apiPrefix;

    public ApiVersionRequestMappingHandlerMapping(String contextPath, String apiPrefix) {
        this.contextPath = contextPath;
        this.apiPrefix = apiPrefix;
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo requestMappingInfo = super.getMappingForMethod(method, handlerType);
        if (requestMappingInfo != null) {

        }
        return requestMappingInfo;
    }

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createApiCondition(apiVersion);
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createApiCondition(apiVersion);
    }

    private RequestCondition<?> createApiCondition(ApiVersion apiVersion) {
        return new ApiVersionCondition(apiVersion);
//        return apiVersion == null ? null : new ApiVersionCondition(apiVersion.value(),apiVersion.backwardCompatible());
    }
}
