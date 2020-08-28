package com.batis.library.handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler {

    public static byte[] readFile(String filepath) throws IOException {
        return Files.readAllBytes(Paths.get(filepath));
    }
}
