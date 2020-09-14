//package com.batis.application.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.support.FormattingConversionService;
//import org.springframework.web.accept.ContentNegotiationManager;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//import org.springframework.web.servlet.resource.ResourceUrlProvider;
//
//@Configuration
//public class WebMvcConfig extends WebMvcConfigurationSupport {
//    @Value("${properties.context-path:api}")
//    private String contextPath;
//    @Value("${properties.api-prefix:v}")
//    private String apiPrefix;
//
//    @Bean
//    @Override
//    public RequestMappingHandlerMapping requestMappingHandlerMapping(ContentNegotiationManager contentNegotiationManager, FormattingConversionService conversionService, ResourceUrlProvider resourceUrlProvider) {
//        return super.requestMappingHandlerMapping(contentNegotiationManager, conversionService, resourceUrlProvider);
//    }
//}
