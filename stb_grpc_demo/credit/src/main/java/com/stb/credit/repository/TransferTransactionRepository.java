package com.stb.credit.repository;

import com.stb.credit.entity.TransferMoneyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferTransactionRepository extends JpaRepository<TransferMoneyTransaction, Long> {
}
