package com.stb.credit.service.interservice;

import com.stb.debit.CreditMoneyRequest;
import com.stb.debit.CreditMoneyResponse;
import com.stb.debit.DebitAccountServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DebitService {
    @GrpcClient("debit-service")
    private DebitAccountServiceGrpc.DebitAccountServiceBlockingStub serviceBlockingStub;

    public CreditMoneyResponse creditAccount(String accountId, double amount){
        try {
            CreditMoneyRequest request = CreditMoneyRequest.newBuilder()
                    .setAccountId(accountId)
                    .setCreditAmount(amount).build();
            var response = this.serviceBlockingStub.creditMoney(request);
            if (response.hasError()) {
                throw new RuntimeException(response.getError().getErrMessage());
            }
            return response;

        } catch (Exception ex){
            log.error("Exception when call to creditMoney in DebitAccountServiceGrpc: ", ex);
            return null;
        }
    }
}
