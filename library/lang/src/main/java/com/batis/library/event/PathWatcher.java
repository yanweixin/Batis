package com.batis.library.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.*;

public class PathWatcher implements GenericWatcher<Path> {
    private final static Logger LOGGER = LoggerFactory.getLogger(PathWatcher.class);
    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;
    private final Path watchingDir;
    private final boolean recursive;
    private boolean trace = false;
    private boolean enabled;

    public PathWatcher(boolean recursive, Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<>();
        this.watchingDir = dir;
        this.recursive = recursive;
    }

    public static PathWatcher getInstance(boolean recursive, Path dir) throws IOException {
        final PathWatcher INSTANCE = new PathWatcher(recursive, dir);
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>) event;
    }

    @Override
    public synchronized void start() {
        if (recursive) {
            LOGGER.debug("Scanning {} and its sub-directories...", watchingDir);
            registerAll(watchingDir);
            LOGGER.debug("Scanning finished!");
        } else {
            register(watchingDir);
        }
        // enable trace after initial registration
        this.trace = true;
        // start processing events
        this.enabled = true;
        processEvents();
    }

    @Override
    public void register(final Path dir) {
        try {
            WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
            if (trace) {
                Path prev = keys.get(key);
                if (prev == null) {
                    LOGGER.debug("Register: {}", dir);
                } else {
                    if (!dir.equals(prev)) {
                        LOGGER.debug("Update：{} -> {}", prev, dir);
                    }
                }
            }
            keys.put(key, dir);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }

    private void registerAll(final Path start) {
        try {
            Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    register(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }

    public void operation(Path file, WatchEvent.Kind kind) {
        LOGGER.info("{}:{}", file.toString(), kind.name());
    }

    @Override
    public void processEvents() {
        while (enabled) {
            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            } catch (Throwable e) {
                e.printStackTrace();
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                LOGGER.error("WatchKey not recognized!");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                    continue;
                }

                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);

                // Actions according to different file operations
                operation(child, kind);

                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (recursive && (kind == ENTRY_CREATE)) {
                    if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                        registerAll(child);
                    }
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);
                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }

    @Override
    public synchronized void stop() {
        this.enabled = false;
    }
}
