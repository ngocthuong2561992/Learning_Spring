package com.stb.credit.service.endpoint;

import com.stb.credit.CreditAccountRequest;
import com.stb.credit.CreditAccountResponse;
import com.stb.credit.CreditAccountServiceGrpc;
import com.stb.credit.service.TransferTransactionService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
@Slf4j
public class TransferTransactionEndpoint extends CreditAccountServiceGrpc.CreditAccountServiceImplBase {

    @Autowired
    private TransferTransactionService transferService;

    @Override
    public void creditAccount(CreditAccountRequest request, StreamObserver<CreditAccountResponse> responseObserver) {
        log.info("creditAccount request: {}", request);
        responseObserver.onNext(transferService.creditAccount(request));
        responseObserver.onCompleted();
    }
}
