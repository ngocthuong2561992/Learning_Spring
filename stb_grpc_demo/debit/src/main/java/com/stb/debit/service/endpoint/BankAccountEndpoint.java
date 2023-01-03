package com.stb.debit.service.endpoint;

import com.stb.debit.CreditMoneyRequest;
import com.stb.debit.CreditMoneyResponse;
import com.stb.debit.DebitAccountInfoRequest;
import com.stb.debit.DebitAccountInfoResponse;
import com.stb.debit.DebitAccountInfoUpdateRequest;
import com.stb.debit.DebitAccountServiceGrpc;
import com.stb.debit.service.BankAccountService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
@Slf4j
public class BankAccountEndpoint extends DebitAccountServiceGrpc.DebitAccountServiceImplBase {

    @Autowired
    private BankAccountService bankAccountService;

    @Override
    public void getAccountInfo(DebitAccountInfoRequest request, StreamObserver<DebitAccountInfoResponse> responseObserver) {
        log.info("getAccountInfo request: {}", request);
        responseObserver.onNext(bankAccountService.getAccountInfo(request));
        responseObserver.onCompleted();
    }

    @Override
    public void debitMoney(DebitAccountInfoUpdateRequest request, StreamObserver<DebitAccountInfoResponse> responseObserver) {
        log.info("debitMoney request: {}", request);
        responseObserver.onNext(bankAccountService.debitMoney(request));
        responseObserver.onCompleted();
    }

    @Override
    public void creditMoney(CreditMoneyRequest request, StreamObserver<CreditMoneyResponse> responseObserver) {
        log.info("creditMoney request: {}", request);
        responseObserver.onNext(bankAccountService.creditAccount(request));
        responseObserver.onCompleted();
    }
}
