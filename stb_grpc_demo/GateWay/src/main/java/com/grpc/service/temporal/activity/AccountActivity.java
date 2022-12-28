package com.grpc.service.temporal.activity;

import com.stb.credit.CreditAccountRequest;
import com.stb.credit.CreditAccountResponse;
import com.stb.debit.DebitAccountInfoResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface AccountActivity {
    @ActivityMethod
    DebitAccountInfoResponse debitMoney(String accountId, double amount);

    @ActivityMethod
    CreditAccountResponse creditAccount(CreditAccountRequest request);
}
