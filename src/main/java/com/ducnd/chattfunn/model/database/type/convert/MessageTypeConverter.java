package com.ducnd.chattfunn.model.database.type.convert;

import com.ducnd.chattfunn.model.database.type.MessageType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MessageTypeConverter implements AttributeConverter<MessageType, String> {
    @Override
    public String convertToDatabaseColumn(MessageType messageType) {
        if (messageType == null) {
            return null;
        }
        return messageType.getValue();
    }

    @Override
    public MessageType convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        for (MessageType value : MessageType.values()) {
            if (value.getValue().equals(s)) {
                return value;
            }
        }
        return null;
    }
}
