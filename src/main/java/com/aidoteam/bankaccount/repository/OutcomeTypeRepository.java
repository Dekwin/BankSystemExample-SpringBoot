package com.aidoteam.bankaccount.repository;

import com.aidoteam.bankaccount.model.IncomeTypeEntity;
import com.aidoteam.bankaccount.model.OutcomeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutcomeTypeRepository extends JpaRepository<OutcomeTypeEntity, Long> {
    List<OutcomeTypeEntity> findByTitleIgnoreCaseContaining(String starts);
    OutcomeTypeEntity findById(Long id);

}
