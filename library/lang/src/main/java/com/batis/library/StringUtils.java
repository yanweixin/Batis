package com.batis.library;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class StringUtils {

    public static boolean startWith(String source, List<String> prefixes) {
        return prefixes.stream().anyMatch(source::startsWith);
    }

    public static String decodeBytes(byte[] bytes, String charset) {
        if (charset == null) {
            return decodeBytes(bytes, StandardCharsets.UTF_8);
        } else {
            return decodeBytes(bytes, Charset.forName(charset));
        }
    }

    private static String decodeBytes(byte[] bytes, Charset charset) {
        return new String(bytes, charset);
    }
}
