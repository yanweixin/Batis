package com.batis.application.config;

import com.batis.application.web.ApiVersionRequestMappingHandlerMapping;
import com.batis.application.web.controller.management.PermissionController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.function.Predicate;

/**
 * Basic : implements {@link WebMvcConfigurer} and {@link EnableWebMvc}
 * More advance : extend DelegatingWebMvcConfiguration
 * Most advanced : extend WebMvcConfigurationSupport
 */
@Configuration
public class WebMvcConfig extends DelegatingWebMvcConfiguration {
    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        configurer
                .setUseTrailingSlashMatch(false)
                .addPathPrefix("/api", Predicate
                        .not(HandlerTypePredicate.forBasePackageClass(PermissionController.class))
                        .and(HandlerTypePredicate.forAnnotation(RestController.class)));
        super.configurePathMatch(configurer);
    }

//   @Bean
//    public UrlPathHelper urlPathHelper() {
//        //...
//
//    }

//    @Bean
//    public PathMatcher antPathMatcher() {
//
//    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(new PageableHandlerMethodArgumentResolver());
//    }
}
