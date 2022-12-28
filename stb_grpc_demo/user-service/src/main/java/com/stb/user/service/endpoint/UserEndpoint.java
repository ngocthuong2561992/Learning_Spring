package com.stb.user.service.endpoint;

import com.stb.user.GetUserRequest;
import com.stb.user.GetUserResponse;
import com.stb.user.UserServiceGrpc;
import com.stb.user.service.UserService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
@RequiredArgsConstructor
public class UserEndpoint extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;

    @Override
    public void getUser(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {
        log.info("getUser request: {}", request);
        responseObserver.onNext(userService.getUser(request));
        responseObserver.onCompleted();
    }
}
