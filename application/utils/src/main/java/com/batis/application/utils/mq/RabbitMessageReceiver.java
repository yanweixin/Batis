package com.batis.application.utils.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"#{defaultQueue.name}", "#{autoDeleteQueue.name}"})
public class RabbitMessageReceiver implements MessageReceiver {
    @RabbitHandler
    @Override
    public void receive(Object in) {
    }
}
