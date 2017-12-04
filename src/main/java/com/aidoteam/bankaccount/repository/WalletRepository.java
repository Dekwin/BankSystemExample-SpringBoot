package com.aidoteam.bankaccount.repository;

import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.model.WalletEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WalletRepository extends CrudRepository<WalletEntity, Long>

    {

        List<WalletEntity> findByLastName(String lastName);
    }

