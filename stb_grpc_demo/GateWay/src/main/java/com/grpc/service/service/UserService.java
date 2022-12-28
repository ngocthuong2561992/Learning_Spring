package com.grpc.service.service;

import com.stb.user.GetUserRequest;
import com.stb.user.GetUserResponse;
import com.stb.user.UserBank;
import com.stb.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    public GetUserResponse getUserInfo(String name, String accNo, String bankName){
        GetUserRequest userSearchRequest = GetUserRequest.newBuilder()
                .setUser(
                        UserBank.newBuilder().setBankName(name).setAccountName(accNo).setBankName(bankName).build()
                )
                .build();

        return this.userStub.getUser(userSearchRequest);
    }

}
