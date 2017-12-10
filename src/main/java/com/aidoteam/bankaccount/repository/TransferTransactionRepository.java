package com.aidoteam.bankaccount.repository;

import com.aidoteam.bankaccount.model.TransferTransactionEntity;
import com.aidoteam.bankaccount.model.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferTransactionRepository extends JpaRepository<TransferTransactionEntity, Long> {
    List<TransferTransactionEntity> findBySenderWalletOrRecipientWallet(WalletEntity senderWallet, WalletEntity recipientWallet);

    List<TransferTransactionEntity> findBySenderWalletAndDatetimeBetween(WalletEntity senderWallet, Long fromDate, Long toDate);
    List<TransferTransactionEntity> findByRecipientWalletAndDatetimeBetween(WalletEntity recipientWallet, Long fromDate, Long toDate);
}
