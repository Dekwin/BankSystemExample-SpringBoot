package com.aidoteam.bankaccount.repository;

import com.aidoteam.bankaccount.model.TransferTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferTransactionRepository extends JpaRepository<TransferTransactionEntity, Long> {

}
