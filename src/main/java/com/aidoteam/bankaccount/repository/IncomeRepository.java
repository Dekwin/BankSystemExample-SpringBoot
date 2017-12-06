package com.aidoteam.bankaccount.repository;

import com.aidoteam.bankaccount.model.IncomeEntity;
import com.aidoteam.bankaccount.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<IncomeEntity, Long> {

    @Query("select u from IncomeEntity u where u.wallet.id = ?1 and u.datetime >= ?2 and u.datetime <= ?3")
    List<IncomeEntity> findByIdBetweenDates(Long id, Long datetimeFrom, Long datetimeTo);

}
