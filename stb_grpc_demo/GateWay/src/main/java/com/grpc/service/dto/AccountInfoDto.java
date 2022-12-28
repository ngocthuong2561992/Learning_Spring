package com.grpc.service.dto;

import lombok.Data;

@Data
public class AccountInfoDto {
    private String accountId;
    private Double currentBalance;
}
