package com.stb.credit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

// mock the data returning
@Builder
@AllArgsConstructor
public class DataTransferResponse {
    private String bankName;
    private String accountNumber;
    private String accountName;
    private double amountTransfer;
    private String status;
    private LocalDateTime transactionDate;
}
