package com.batis.application.utils.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class DateConverter implements Converter<String, Date> {
    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Date convert(String source) {
        return Date.from(LocalDate.parse(source, dateFormatter).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
