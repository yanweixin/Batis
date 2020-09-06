package com.batis.application.database.common;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class KeySpaceNotificationMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.err.println("Received action = " + new String(message.getBody()) + " " +
                " and key info = " + new String(message.getChannel()) + " pattern = " + pattern);
    }
}
