package com.aidoteam.bankaccount.repository;

import com.aidoteam.bankaccount.model.IncomeEntity;
import com.aidoteam.bankaccount.model.OutcomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutcomeRepository  extends JpaRepository<OutcomeEntity, Long> {

    @Query("select u from OutcomeEntity u where u.wallet.id = ?1 and u.datetime >= ?2 and u.datetime <= ?3")
    List<OutcomeEntity> findByWalletIdBetweenDates(Long walletId, Long datetimeFrom, Long datetimeTo);

    @Query("select u from OutcomeEntity u where u.wallet.id = ?1 and u.outcomeType.id = ?2 and u.datetime >= ?3 and u.datetime <= ?4")
    List<OutcomeEntity> findByWalletIdAndOutcomeTypeIdBetweenDates(Long walletId, Long outcomeTypeId, Long datetimeFrom, Long datetimeTo);
}
