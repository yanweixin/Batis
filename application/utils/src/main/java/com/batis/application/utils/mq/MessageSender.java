package com.batis.application.utils.mq;

public interface MessageSender {
    void send(final Object message);
}
