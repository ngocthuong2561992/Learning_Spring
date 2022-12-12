package com.multidb.enums;

import java.util.Arrays;

public enum Gender {
    M( ""),
    F( "");

    private final String detail;

    Gender(String detail) {
        this.detail = detail;
    }

    public static Gender getEnum(String key) {
        return Arrays.stream(values())
                .filter(s -> s.toString().equals(key))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No matching Gender for [" + key + "]"));
    }

    public String getDetail() {
        return detail;
    }
}
