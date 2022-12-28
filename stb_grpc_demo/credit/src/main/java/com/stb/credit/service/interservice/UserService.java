package com.stb.credit.service.interservice;

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
                        UserBank.newBuilder().setAccountName(name).setAccountNumber(accNo).setBankName(bankName).build()
                )
                .build();

        var response = this.userStub.getUser(userSearchRequest);
        if (response.hasError()) {
            throw new RuntimeException(response.getError().getErrMessage());
        }
        return response;
    }

}
