package com.ducnd.chattfunn.model.database.type;

public enum BooleanTypeDb {
    TRUE("true"),
    FALSE("false");
    private final String value;

    BooleanTypeDb(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
