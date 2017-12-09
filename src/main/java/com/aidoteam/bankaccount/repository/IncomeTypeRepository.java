package com.aidoteam.bankaccount.repository;

import com.aidoteam.bankaccount.model.IncomeEntity;
import com.aidoteam.bankaccount.model.IncomeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncomeTypeRepository extends JpaRepository<IncomeTypeEntity, Long> {
   // List<IncomeTypeEntity> getAll();
    List<IncomeTypeEntity> findByTitleIgnoreCaseContaining(String starts);
}
