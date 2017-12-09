package com.aidoteam.bankaccount.service.bank;


public interface WalletService {
    void makeTransferTransaction(Long fromWalletId, Long toWalletId, String toAccount, Long amount, String description);
}
