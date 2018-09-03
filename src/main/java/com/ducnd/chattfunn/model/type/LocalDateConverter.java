package com.ducnd.chattfunn.model.type;

import org.joda.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return new Date(localDate.toDate().getTime());
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        if ( date == null ){
            return null;
        }
        return LocalDate.fromDateFields(date);
    }
}

