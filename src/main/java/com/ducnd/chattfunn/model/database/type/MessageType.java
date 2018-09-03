package com.ducnd.chattfunn.model.database.type;

public enum MessageType {
    TEXT("Text"),
    IMAGE("Image"),
    VIDEO("Video"),
    FILE("File"),
    OTHER("Other");
    private final String value;

    MessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
