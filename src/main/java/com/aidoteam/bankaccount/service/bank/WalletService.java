package com.aidoteam.bankaccount.service.bank;


import com.aidoteam.bankaccount.model.*;

import java.util.List;

public interface WalletService {
    WalletEntity findById(Long id);
    List<WalletEntity> getOwnWallets();
    TransferTransactionEntity makeTransferTransaction(Long fromWalletId, Long toWalletId, String toAccount, Long amount, String description);
    List<TransferTransactionEntity> findTransferTransactionsBetweenDates(Long walletId, Long dateFrom, Long dateTo);
    List<IncomeEntity> findIncomesByWalletIdAndIncomeTypeIdBetweenDates(Long walletId, Long incomeTypeId, Long datetimeFrom, Long datetimeTo) throws IllegalAccessException;
    List<OutcomeEntity> findOutcomesByWalletIdAndOutcomeTypeIdBetweenDates(Long walletId, Long incomeTypeId, Long datetimeFrom, Long datetimeTo) throws IllegalAccessException;
    List<IncomeEntity> findIncomesByWalletIdBetweenDates(Long walletId, Long datetimeFrom, Long datetimeTo) throws IllegalAccessException;
    List<OutcomeEntity> findOutcomesByWalletIdBetweenDates(Long walletId, Long datetimeFrom, Long datetimeTo) throws IllegalAccessException;
    OutcomeEntity makeOutcomePayment(Long walletId, String toAccount,Long amount,Long outcomeTypeId,String description) throws IllegalAccessException;

    List<OutcomeTypeEntity> getAllOutcomeTypes();
}
