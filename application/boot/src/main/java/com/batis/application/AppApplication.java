package com.batis.application;

import com.batis.application.utils.properties.StorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "com.batis")
@EnableConfigurationProperties(StorageProperties.class)
@EnableAsync
public class AppApplication {
    private final static Logger logger = LoggerFactory.getLogger(AppApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

//    @Bean
//    CommandLineRunner init(StorageService storageService) {
//        return (args) -> {
//            storageService.deleteAll();
//            storageService.init();
//        };
//    }

}