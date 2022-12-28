package com.grpc.service.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferMoneyRequest {
    @JsonProperty("fromAccountId")
    private String fromAccountId;

    @JsonProperty("toAccountName")
    private String toAccountName;

    @JsonProperty("toAccountNo")
    private String toAccountNo;

    @JsonProperty("toBankName")
    private String toBankName;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("description")
    private String description;

    @JsonProperty("method")
    private String method;
}
