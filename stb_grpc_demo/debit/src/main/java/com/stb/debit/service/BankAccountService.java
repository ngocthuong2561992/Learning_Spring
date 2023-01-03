package com.stb.debit.service;

import com.stb.common.Error;
import com.stb.debit.CreditMoneyRequest;
import com.stb.debit.CreditMoneyResponse;
import com.stb.debit.DebitAccountInfoRequest;
import com.stb.debit.DebitAccountInfoResponse;
import com.stb.debit.DebitAccountInfoUpdateRequest;
import com.stb.debit.entity.BankAccount;
import com.stb.debit.repository.BankAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BankAccountService {

    @Autowired
    private BankAccountRepository accountRepository;

    public DebitAccountInfoResponse getAccountInfo(DebitAccountInfoRequest request) {
        try {
            DebitAccountInfoResponse.Builder builder = DebitAccountInfoResponse.newBuilder();
            Optional<BankAccount> bankAccountInfo = this.accountRepository.findById(request.getAccountId());

            if(bankAccountInfo.isPresent()){
                var accountInfo = bankAccountInfo.get();
                builder.setAccountId(accountInfo.getAccountId())
                        .setCurrentBalance(accountInfo.getMoneyAmount())
                        .build();
            }
            return builder.build();
        } catch (Exception exception) {
            log.error("exception: ", exception);
            Error error = Error.newBuilder().setErrCode("DEBIT_ERROR")
                    .setErrMessage("An error occurs in getAccountInfo method: " + exception.getMessage()).build();
            return DebitAccountInfoResponse.newBuilder().setError(error).build();
        }
    }

    public DebitAccountInfoResponse debitMoney(DebitAccountInfoUpdateRequest request) {
        try {
            BankAccount bankAccountDebit = this.accountRepository.findById(request.getAccountId())
                    .orElseThrow(() -> new RuntimeException("Cannot found Bank Account with Id = " + request.getAccountId()));

            double newBalance = bankAccountDebit.getMoneyAmount() - request.getDebitAmount();
            if (newBalance < 0) {
                throw new RuntimeException("The amount of account is less than money transferring");
            }

            bankAccountDebit.setMoneyAmount(newBalance);
            var res = accountRepository.saveAndFlush(bankAccountDebit);

            return DebitAccountInfoResponse.newBuilder()
                    .setAccountId(res.getAccountId())
                    .setCurrentBalance(res.getMoneyAmount())
                    .build();

        } catch (Exception exception) {
            log.error("exception: ", exception);
            Error error = Error.newBuilder().setErrCode("DEBIT_ERROR")
                    .setErrMessage("An error occurs in debitMoney method: " + exception.getMessage()).build();
            return DebitAccountInfoResponse.newBuilder().setError(error).build();
        }
    }

    public CreditMoneyResponse creditAccount(CreditMoneyRequest request) {
        try {
            BankAccount bankAccountDebit = this.accountRepository.findById(request.getAccountId())
                    .orElseThrow(() -> new RuntimeException("Cannot found Bank Account with Id = " + request.getAccountId()));

            double newBalance = bankAccountDebit.getMoneyAmount() + request.getCreditAmount();
            bankAccountDebit.setMoneyAmount(newBalance);

            var res = accountRepository.saveAndFlush(bankAccountDebit);

            return CreditMoneyResponse.newBuilder()
                    .setAccountId(res.getAccountId())
                    .setCurrentBalance(res.getMoneyAmount())
                    .build();

        } catch (Exception exception) {
            log.error("exception: ", exception);
            Error error = Error.newBuilder().setErrCode("DEBIT_ERROR")
                    .setErrMessage("An error occurs in creditAccount method: " + exception.getMessage()).build();
            return CreditMoneyResponse.newBuilder().setError(error).build();
        }
    }
}
