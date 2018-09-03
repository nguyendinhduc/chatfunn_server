package com.ducnd.chattfunn.model.database.type.convert;

import com.ducnd.chattfunn.model.database.type.BooleanTypeDb;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanTypeDbConverter implements AttributeConverter<BooleanTypeDb, String> {
    @Override
    public String convertToDatabaseColumn(BooleanTypeDb booleanTypeDb) {
        if (booleanTypeDb == null) {
            return null;
        }
        return booleanTypeDb.getValue();
    }

    @Override
    public BooleanTypeDb convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        for (BooleanTypeDb value : BooleanTypeDb.values()) {
            if (value.getValue().equals(s)) {
                return value;
            }
        }
        return null;
    }
}
