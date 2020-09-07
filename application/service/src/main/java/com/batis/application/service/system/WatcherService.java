package com.batis.application.service.system;

public interface WatcherService<T> {
    void start();

    void register(T t);

    void processEvents();
}
