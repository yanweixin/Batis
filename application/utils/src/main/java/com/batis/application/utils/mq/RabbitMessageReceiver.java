package com.batis.application.utils.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"spring-boot"})
public class RabbitMessageReceiver implements MessageReceiver {
    @RabbitHandler
    @Override
    public void receive(String in) {

    }
}
