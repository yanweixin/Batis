package com.batis.library.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReflectUtils {

    public static <T> List<Field> getAllFields(T t){
        List<Field> fields = new ArrayList<>();
        Class<?> supperClass = t.getClass();
        while (supperClass != null && supperClass != Object.class) {
            Collections.addAll(fields, supperClass.getDeclaredFields());
            supperClass = supperClass.getSuperclass();
        }
        return fields;
    }
}
