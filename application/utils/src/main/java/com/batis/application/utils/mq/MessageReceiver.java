package com.batis.application.utils.mq;

public interface MessageReceiver {
    void receive(String in);
}
