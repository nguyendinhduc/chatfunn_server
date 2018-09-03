package com.ducnd.chattfunn.common.config;

import com.ducnd.chattfunn.common.Constants;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern(Constants.FORMAT_DATE);

    @Override
    public LocalDate convert(String s) {
        return LocalDate.parse(s, FORMATTER);
    }
}

