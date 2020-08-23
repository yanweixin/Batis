package com.batis.library.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReflectUtils {

    @SuppressWarnings("unchecked")
    public static <T> List<Field> getAllFields(T t) {
        List<Field> fields = new ArrayList<>();
        Class<?> supperClass = t.getClass();
        while (supperClass != null && supperClass != Object.class) {
            Collections.addAll(fields, supperClass.getDeclaredFields());
            supperClass = supperClass.getSuperclass();
        }
        return fields;
    }

    @SuppressWarnings("unchecked")
    public static <T, A extends Annotation> String[] getAnnotationFields(T source, Class<A>... annotationClasses) {
        List<String> annotationFields = new ArrayList<>();
        for (Field field : getAllFields(source)) {
            for (Class<A> clazz : annotationClasses) {
                if (field.getAnnotation(clazz) != null) {
                    annotationFields.add(field.getName());
                }
            }
        }
        return annotationFields.toArray(String[]::new);
    }
}
