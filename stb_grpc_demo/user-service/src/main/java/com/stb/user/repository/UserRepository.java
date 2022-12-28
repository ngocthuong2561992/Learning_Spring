package com.stb.user.repository;

import com.stb.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByNameAndAccountNumberAndBankName(String name, String accountNumber, String bankName);
}
