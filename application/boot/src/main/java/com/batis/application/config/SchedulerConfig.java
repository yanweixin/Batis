package com.batis.application.config;

import com.batis.application.utils.mq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    @Autowired
    MessageSender rabbitMessageSender;
    @Autowired
    MessageSender fanoutMessageSender;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        String message = "Hello World!";
//        rabbitMessageSender.send(message);
//        fanoutMessageSender.send(message);
    }
}
