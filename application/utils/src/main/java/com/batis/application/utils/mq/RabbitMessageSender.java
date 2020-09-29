package com.batis.application.utils.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessageSender implements MessageSender {
    @Autowired
    private RabbitTemplate template;

    @Qualifier("defaultQueue")
    @Autowired
    private Queue queue;

    @Autowired
    private Queue autoDeleteQueue;

    @Override
    public void send(Object message) {
        this.template.convertAndSend(this.queue.getName(),message);
    }
}
