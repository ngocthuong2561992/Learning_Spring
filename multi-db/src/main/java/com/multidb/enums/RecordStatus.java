package com.multidb.enums;

import java.util.Arrays;

public enum RecordStatus {
    SUCCESS("SUCCESS", "SUCCESS"),
    ERROR("ERROR", "ERROR"),
    FAILED("FAILED", "FAILED");

    private final String value;
    private final String description;

    RecordStatus(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static RecordStatus getEnum(String key) {
        return Arrays.stream(values())
                .filter(s -> s.getValue().equals(key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No matching RecordStatus for [" + key + "]"));
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
