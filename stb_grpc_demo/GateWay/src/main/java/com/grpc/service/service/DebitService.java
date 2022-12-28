package com.grpc.service.service;

import com.stb.debit.DebitAccountInfoResponse;
import com.stb.debit.DebitAccountInfoUpdateRequest;
import com.stb.debit.DebitAccountServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DebitService {
    @GrpcClient("debit-service")
    private DebitAccountServiceGrpc.DebitAccountServiceBlockingStub serviceBlockingStub;

    public DebitAccountInfoResponse debitMoney(String accountId, double amount) {
        try {
            DebitAccountInfoUpdateRequest request = DebitAccountInfoUpdateRequest.newBuilder()
                    .setAccountId(accountId)
                    .setDebitAmount(amount)
                    .build();
            var response = this.serviceBlockingStub.debitMoney(request);
            if (response.hasError()) {
                throw new RuntimeException(response.getError().getErrMessage());
            }
            return response;

        } catch (Exception ex){
            log.error("Exception when call to debitMoney in DebitAccountServiceGrpc: ", ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
}
