package com.ducnd.chattfunn.common.config.time;

import com.ducnd.chattfunn.common.Constants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {
    private static final SimpleDateFormat format = new SimpleDateFormat(Constants.FORMAT_DATE);

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeString(format.format(localDate.toDate()));
    }
}