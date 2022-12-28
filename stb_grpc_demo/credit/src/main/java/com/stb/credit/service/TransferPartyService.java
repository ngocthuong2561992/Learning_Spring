package com.stb.credit.service;

import com.stb.credit.dto.response.DataTransferResponse;
import com.stb.credit.entity.TransferMoneyTransaction;
import com.stb.credit.enums.TransferMoneyStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class TransferPartyService {
    public DataTransferResponse doTransferMoney(TransferMoneyTransaction request, TransferMoneyStatus mockStatus) throws InterruptedException {
        log.info("money transferring is processing ......  - Sleep 2s.");
        Thread.sleep(2000);

        var builderResp = DataTransferResponse.builder()
                .accountName(request.getToAccountName())
                .accountNumber(request.getToAccountNumber())
                .bankName(request.getToBankName())
                .amountTransfer(request.getAmount())
                .transactionDate(LocalDateTime.now());

        switch(mockStatus) {
            case SUCCESS: {
                builderResp.status(TransferMoneyStatus.SUCCESS.getStatus());
            }
            case FAILED: {
                builderResp.status(TransferMoneyStatus.FAILED.getStatus());
            }
            case TIMEOUT: {
                builderResp.status(TransferMoneyStatus.TIMEOUT.getStatus());
            }
        }

        return builderResp.build();
    }
}
