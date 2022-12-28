package com.grpc.service.temporal.activity;

import com.grpc.service.service.CreditService;
import com.grpc.service.service.DebitService;
import com.stb.credit.CreditAccountRequest;
import com.stb.credit.CreditAccountResponse;
import com.stb.debit.DebitAccountInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AccountActivityImpl implements AccountActivity {

    private final DebitService debitService;
    private final CreditService creditService;

    @Override
    public DebitAccountInfoResponse debitMoney(String accountId, double amount) {
        log.info("AccountActivityImpl: executing debitMoney ...");
        return debitService.debitMoney(accountId, amount);
    }

    @Override
    public CreditAccountResponse creditAccount(CreditAccountRequest request) {
        log.info("AccountActivityImpl: executing creditAccount ...");
        return creditService.creditAccount(request);
    }
}
