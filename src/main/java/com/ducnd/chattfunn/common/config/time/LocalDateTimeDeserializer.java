package com.ducnd.chattfunn.common.config.time;

import com.ducnd.chattfunn.common.Constants;
import com.ducnd.chattfunn.common.utils.StringUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern(Constants.FORMAT_DATE_TIME);

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        String value = jsonParser.getValueAsString();
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return LocalDateTime.parse(value, FORMATTER);
    }
}
