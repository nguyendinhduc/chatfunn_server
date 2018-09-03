package com.ducnd.chattfunn.common.config;

import com.ducnd.chattfunn.common.Constants;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    private static final Logger LOG = LoggerFactory.getLogger(StringToLocalDateTimeConverter.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern(Constants.FORMAT_DATE_TIME);

    @Override
    public LocalDateTime convert(String s) {
        if (s == null) {
            return null;
        }
        return LocalDateTime.parse(s, FORMATTER);
    }
}

