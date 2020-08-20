package com.batis.library.time;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtils {
    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final static DateTimeFormatter isoDateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;

    public static Date stringToDate(String source) {
        return Date.from(LocalDate.parse(source, dateFormatter).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date[] stringToDate(String[] sources) {
        if (sources.length == 0) {
            throw new IndexOutOfBoundsException("Array length must be greater than zero");
        }
        Date[] dates = new Date[sources.length];
        for (int i = 0; i < sources.length; i++) {
            dates[i] = stringToDate(sources[i]);
        }
        return dates;
    }
}
