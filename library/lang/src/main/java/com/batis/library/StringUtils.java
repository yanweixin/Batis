package com.batis.library;

import java.util.List;

public class StringUtils {

    public static boolean startWith(String source, List<String> prefixes) {
        return prefixes.stream().anyMatch(source::startsWith);
    }
}
