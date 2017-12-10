package com.aidoteam.bankaccount.service.bank;

import com.aidoteam.bankaccount.model.OutcomeEntity;

import java.util.List;

public interface OutcomeService {
    List<OutcomeEntity> findByWalletIdBetweenDates(Long walletId, Long datetimeFrom, Long datetimeTo);
    List<OutcomeEntity> findByWalletIdAndOutcomeTypeIdBetweenDates(Long walletId, Long outcomeTypeId, Long datetimeFrom, Long datetimeTo);
    void create(Long walletId, String toAccount,Long amount,Long outcomeTypeId,String description) throws IllegalAccessException;
}
