package com.grpc.service.temporal.workflow;

import com.grpc.service.dto.request.TransferMoneyRequest;
import com.grpc.service.dto.response.TransferMoneyResponse;
import com.grpc.service.temporal.activity.AccountActivity;
import com.stb.credit.CreditAccountRequest;
import com.stb.credit.TransferMethod;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
public class MoneyTransferWorkflowImpl implements MoneyTransferWorkflow {

    private static final String TRANSFER = "transfer-money";
    // RetryOptions specify how to automatically handle retries when Activities fail.
//    private final RetryOptions retryoptions = RetryOptions.newBuilder()
//            .setInitialInterval(Duration.ofSeconds(1))
//            .setMaximumInterval(Duration.ofSeconds(100))
//            .setBackoffCoefficient(2)
//            .setMaximumAttempts(500)
//            .build();
//    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
//            // Timeout options specify when to automatically timeout Activities if the process is taking too long.
//            .setStartToCloseTimeout(Duration.ofSeconds(5))
//            // Optionally provide customized RetryOptions.
//            // Temporal retries failures by default, this is simply an example.
//            .setRetryOptions(retryoptions)
//            .build();
//    // ActivityStubs enable calls to methods as if the Activity object is local, but actually perform an RPC.
//    private final Map<String, ActivityOptions> perActivityMethodOptions = new HashMap<String, ActivityOptions>(){{
//        put(TRANSFER, ActivityOptions.newBuilder().setHeartbeatTimeout(Duration.ofSeconds(5)).build());
//    }};
    // private final AccountActivity account = Workflow.newActivityStub(AccountActivity.class, defaultActivityOptions);

    private final RetryOptions retryoptions = RetryOptions.newBuilder().setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100)).setBackoffCoefficient(2).setMaximumAttempts(500).build();
    private final ActivityOptions options = ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(30))
            .setRetryOptions(retryoptions).build();

    private final AccountActivity account = Workflow.newActivityStub(AccountActivity.class, options);

    @Override
    public TransferMoneyResponse transferMoney(TransferMoneyRequest request) {
        // Debit
        log.info("Start workflow money transfer ....");
        log.info("Step 1: Start Debit account Id = " + request.getFromAccountId() + "with amount = " + request.getAmount());
        var debitReps = account.debitMoney(request.getFromAccountId(), request.getAmount());
        log.info("Step 1: End Debit account Id = " + debitReps.getAccountId() + "with amount = " + request.getAmount());

        CreditAccountRequest creditAccountRequest = CreditAccountRequest.newBuilder()
                        .setFromAccountId(request.getFromAccountId())
                        .setToAccountName(request.getToAccountName())
                        .setToAccountNumber(request.getToAccountNo())
                        .setToBankName(request.getToBankName())
                        .setAmount(request.getAmount())
                        .setDescription(request.getDescription())
                        .setMethod(request.getMethod().equalsIgnoreCase("instant") ? TransferMethod.INSTANT : TransferMethod.NAPAS)
                                .build();

        // Credit
        log.info(String.format("Step 2: Start credit account name = %s, account no = %s, bank = %s, method = %s"
                , request.getToAccountName(), request.getToAccountNo(), request.getToBankName(), request.getMethod()));
        var response = account.creditAccount(creditAccountRequest);
        log.info(String.format("Step 2: End credit account. Transaction created with id = %s, Ref_id = %s, status = %s"
                , response.getTransactionId(), response.getRefId(), response.getStatus()));

        return TransferMoneyResponse.builder()
                .transaction_id(response.getTransactionId())
                .ref_id(response.getRefId())
                .status(response.getStatus())
                .fromAccountId(request.getFromAccountId())
                .toAccountName(request.getToAccountName())
                .toAccountNo(request.getToAccountNo())
                .toBankName(request.getToBankName())
                .method(request.getMethod())
                .message(request.getDescription())
                .amount(request.getAmount())
                .createdDate(LocalDateTime.now())
                .build();
    }
}
