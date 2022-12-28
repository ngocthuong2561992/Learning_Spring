package com.stb.user.service;


import com.stb.common.Error;
import com.stb.user.GetUserRequest;
import com.stb.user.GetUserResponse;
import com.stb.user.UserBank;
import com.stb.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public GetUserResponse getUser(GetUserRequest request) {
        try {
            var userRequest = request.getUser();
            var users = userRepository.findAllByNameAndAccountNumberAndBankName(userRequest.getAccountName()
                                                            , userRequest.getAccountNumber(), userRequest.getBankName());
            if (CollectionUtils.isEmpty(users)) {
                throw new RuntimeException(String.format("Cannot found the User by accountNo = %s, bank name = %s"
                        , userRequest.getAccountNumber(), userRequest.getBankName()));
            }
            var userResp = users.get(0);
            return GetUserResponse.newBuilder()
                    .setUser(
                            UserBank.newBuilder()
                                    .setId(userResp.getId())
                                    .setAccountName(userResp.getName())
                                    .setAccountNumber(userResp.getAccountNumber())
                                    .setBankName(userResp.getBankName())
                                    .build()
                    )
                    .build();
        } catch (Exception exception) {
            log.error("exception: ", exception);
            Error error = Error.newBuilder().setErrCode("USER_ERROR")
                    .setErrMessage("An error occurs in getUser method: " + exception.getMessage()).build();
            return GetUserResponse.newBuilder().setError(error).build();
        }
    }
}
