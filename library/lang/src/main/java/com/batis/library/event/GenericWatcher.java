package com.batis.library.event;

public interface GenericWatcher<T> {
    void start();

    void register(T t);

    void processEvents();

    void stop();
}
