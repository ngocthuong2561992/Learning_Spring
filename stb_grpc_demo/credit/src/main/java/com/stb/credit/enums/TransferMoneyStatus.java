package com.stb.credit.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

@AllArgsConstructor
@Getter
public enum TransferMoneyStatus {

    SUCCESS("success"),
    FAILED("failed"),
    TIMEOUT("time-out"),
    UNSPECIFIED("")
    ;

    private String status;

    public static TransferMoneyStatus getTransferMoneyStatusFromString(String value) {
        if (!StringUtils.hasText(value)) return UNSPECIFIED;
        for(TransferMoneyStatus enumStatus : TransferMoneyStatus.values()) {
            if (enumStatus.getStatus().equalsIgnoreCase(value)) {
                return enumStatus;
            }
        }

        return UNSPECIFIED;
    }
}
