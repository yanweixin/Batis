package com.batis.application.service.system;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path getRootLocation();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
}
