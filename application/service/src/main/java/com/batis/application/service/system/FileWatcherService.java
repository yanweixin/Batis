package com.batis.application.service.system;

import com.batis.library.event.PathWatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.concurrent.Executor;

@Service
public class FileWatcherService {
    private final PathWatcher pathWatcher;

    public FileWatcherService(StorageService storageService, Executor executor) throws IOException {
        this.pathWatcher = new PathWatcher(true, storageService.getRootLocation()) {
            @Override
            public void operation(Path file, WatchEvent.Kind<?> kind) {
                super.operation(file, kind);
            }
        };
        executor.execute(this.pathWatcher::start);
    }
}
