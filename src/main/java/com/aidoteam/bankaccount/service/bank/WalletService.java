package com.aidoteam.bankaccount.service.bank;


import com.aidoteam.bankaccount.model.IncomeEntity;
import com.aidoteam.bankaccount.model.TransferTransactionEntity;
import com.aidoteam.bankaccount.model.WalletEntity;

import java.util.List;

public interface WalletService {
    WalletEntity findById(Long id);
    List<WalletEntity> getOwnWallets();
    void makeTransferTransaction(Long fromWalletId, Long toWalletId, String toAccount, Long amount, String description);
    List<TransferTransactionEntity> findTransferTransactionsBetweenDates(Long walletId, Long dateFrom, Long dateTo);
    List<IncomeEntity> findIncomesByWalletIdAndIncomeTypeIdBetweenDates(Long walletId, Long incomeTypeId, Long datetimeFrom, Long datetimeTo) throws IllegalAccessException;
    List<IncomeEntity> findIncomesByWalletIdBetweenDates(Long walletId, Long datetimeFrom, Long datetimeTo) throws IllegalAccessException;
}
