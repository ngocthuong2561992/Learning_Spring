package com.redisexampleapp.service;

import com.redisexampleapp.model.UsersModel;
import com.redisexampleapp.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersModel save(UsersModel user) {
        return usersRepository.save(user);
    }

    public List<UsersModel> findAll() {
        return usersRepository.findAll();
    }
}
