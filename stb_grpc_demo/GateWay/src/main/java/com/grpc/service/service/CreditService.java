package com.grpc.service.service;

import com.stb.credit.CreditAccountRequest;
import com.stb.credit.CreditAccountResponse;
import com.stb.credit.CreditAccountServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreditService {

    @GrpcClient("credit-service")
    private CreditAccountServiceGrpc.CreditAccountServiceBlockingStub blockingStub;

    public CreditAccountResponse creditAccount(CreditAccountRequest request) {
        try {
            var response = this.blockingStub.creditAccount(request);
            if (response.hasError()) {
                throw new RuntimeException(response.getError().getErrMessage());
            }
            return response;

        } catch (Exception ex){
            log.error("Exception when call to creditAccount in CreditAccountServiceGrpc: ", ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
}
