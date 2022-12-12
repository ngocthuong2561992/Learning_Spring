package com.jpa.enumerator;

import java.util.Arrays;

public enum Status {
    NEW("NE", ""),
    PENDING("PE", "");

    private final String code;
    private final String detail;

    Status(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public static Status getEnum(String code) {
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
