package com.batis.application.service.impl.file;

import com.batis.application.service.system.WatcherService;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class FileWatchService implements WatcherService<Path> {
    @Override
    public void start() {

    }

    @Override
    public void register(final Path dir) {

    }

    public void registerAll(final Path dir) {
    }

    @Override
    public void processEvents() {

    }
}
