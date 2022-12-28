package com.grpc.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferMoneyResponse {
    private String transaction_id;
    private String ref_id;
    private String fromAccountId;
    private String toAccountName;
    private String toAccountNo;
    private String toBankName;
    private String status;
    private String message;
    private String method;
    private double amount;
    private LocalDateTime createdDate;
}
