package com.aidoteam.bankaccount.repository;

import com.aidoteam.bankaccount.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

        List<UserEntity> findByLastName(String lastName);
}

