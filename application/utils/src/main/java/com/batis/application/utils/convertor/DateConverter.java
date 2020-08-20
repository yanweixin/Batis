package com.batis.application.utils.convertor;

import com.batis.library.time.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        return DateUtils.stringToDate(source);
    }
}
