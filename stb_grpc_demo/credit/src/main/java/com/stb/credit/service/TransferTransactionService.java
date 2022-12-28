package com.stb.credit.service;

import com.stb.common.Error;
import com.stb.credit.CreditAccountRequest;
import com.stb.credit.CreditAccountResponse;
import com.stb.credit.TransferMethod;
import com.stb.credit.entity.TransferMoneyTransaction;
import com.stb.credit.enums.TransferMoneyStatus;
import com.stb.credit.repository.TransferTransactionRepository;
import com.stb.credit.service.interservice.DebitService;
import com.stb.credit.service.interservice.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class TransferTransactionService {

    private final TransferTransactionRepository transferRepository;

    private final TransferPartyService transferPartyService;

    private final DebitService debitService;
    private final UserService userService;

    @Transactional
    public CreditAccountResponse creditAccount(CreditAccountRequest request) {
        try {
            TransferMoneyTransaction transaction = TransferMoneyTransaction.builder()
                    .fromAccount(request.getFromAccountId())
                    .toAccountName(request.getToAccountName())
                    .toBankName(request.getToBankName())
                    .toAccountNumber(request.getToAccountNumber())
                    .amount(request.getAmount())
                    .description(request.getDescription())
                    .referenceId(UUID.randomUUID().toString())
                    .build();

            if (request.getMethod().equals(TransferMethod.INSTANT)) {
                // in the same bank
                return this.instantCreditAccount(transaction);
            }

            // inter-bank
            return this.napasCreditAccount(transaction);

        } catch (Exception exception) {
            log.error("exception: ", exception);
            Error error = Error.newBuilder().setErrCode("DEBIT_ERROR")
                    .setErrMessage("An error occurs in creditAccount method: " + exception.getMessage()).build();
            return CreditAccountResponse.newBuilder().setError(error).build();
        }
    }

    private CreditAccountResponse instantCreditAccount(TransferMoneyTransaction transaction) {
        // call to UserService to get user info
        var userCreditInfo = userService.getUserInfo(transaction.getToAccountName()
                , transaction.getToAccountNumber(), transaction.getToBankName());

        debitService.creditAccount(userCreditInfo.getUser().getId(), transaction.getAmount());
        transaction.setStatus(TransferMoneyStatus.SUCCESS);

        var transactionResp = transferRepository.saveAndFlush(transaction);

        return CreditAccountResponse.newBuilder()
                .setTransactionId(transactionResp.getId().toString())
                .setRefId(transactionResp.getReferenceId())
                .setStatus(transactionResp.getStatus().toString())
                .build();
    }

    private CreditAccountResponse napasCreditAccount(TransferMoneyTransaction transaction) throws InterruptedException {

        // mock hàng động giả cho 3rd (như Napas ...)
        if (transaction.getFromAccount().contains("001")) {
            transferPartyService.doTransferMoney(transaction, TransferMoneyStatus.SUCCESS);
            transaction.setStatus(TransferMoneyStatus.SUCCESS);

        } else if (transaction.getFromAccount().contains("002")) {
            transferPartyService.doTransferMoney(transaction, TransferMoneyStatus.FAILED);
            transaction.setStatus(TransferMoneyStatus.FAILED);
            // revert money for fromAccount
            debitService.creditAccount(transaction.getFromAccount(), transaction.getAmount());

        } else {
            transferPartyService.doTransferMoney(transaction, TransferMoneyStatus.TIMEOUT);
            transaction.setStatus(TransferMoneyStatus.TIMEOUT);
        }

        var transactionResp = transferRepository.saveAndFlush(transaction);
        return CreditAccountResponse.newBuilder()
                .setTransactionId(transactionResp.getId().toString())
                .setRefId(transactionResp.getReferenceId())
                .setStatus(transactionResp.getStatus().toString())
                .build();
    }
}
