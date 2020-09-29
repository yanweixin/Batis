package com.batis.application.utils.mq;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FanoutMessageSender implements MessageSender {
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private Exchange fanoutExchange;

    @Qualifier("defaultQueue")
    @Autowired
    private Queue queue;

    @Override
    public void send(Object message) {
        this.template.convertAndSend(this.fanoutExchange.getName(), this.queue.getName(), message);
    }
}
