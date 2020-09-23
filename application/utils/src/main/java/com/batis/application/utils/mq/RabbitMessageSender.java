package com.batis.application.utils.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessageSender implements MessageSender{
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Override
    public void send(String message) {
        this.template.convertAndSend(this.queue.getName(), message);
    }
}
