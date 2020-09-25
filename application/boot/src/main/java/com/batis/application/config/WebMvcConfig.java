package com.batis.application.config;

import com.batis.application.web.controller.management.PermissionController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.function.Predicate;

/**
 * More advance : extend DelegatingWebMvcConfiguration
 * Most advanced : extend WebMvcConfigurationSupport
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer
                .setUseTrailingSlashMatch(false)
                .addPathPrefix("/api", Predicate
                        .not(HandlerTypePredicate.forBasePackageClass(PermissionController.class))
                        .and(HandlerTypePredicate.forAnnotation(RestController.class)));
    }

//    @Bean
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
