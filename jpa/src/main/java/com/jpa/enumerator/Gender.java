package com.jpa.enumerator;

import java.util.Arrays;

public enum Gender {
    MALE("M", ""),
    FEMALE("F", "");

    private final String code;
    private final String detail;

    Gender(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public static Gender getEnum(String code) {
        return Arrays.stream(values())
                .filter(s -> s.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No matching Gender for [" + code + "]"));
    }

    public String getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }
}
