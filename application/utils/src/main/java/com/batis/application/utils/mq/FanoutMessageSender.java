package com.batis.application.utils.mq;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutMessageSender implements MessageSender {
    private final String[] keys = {"Broadcast", "black", "green"};
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private Exchange fanoutExchange;

    @Override
    public void send(Object message) {
        this.template.convertAndSend(this.fanoutExchange.getName(), keys[0], message);
    }
}
