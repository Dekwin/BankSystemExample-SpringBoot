package com.aidoteam.bankaccount.repository;

import com.aidoteam.bankaccount.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public class UserRepository {
    public interface CustomerRepository extends CrudRepository<UserEntity, Long> {

        List<UserEntity> findByLastName(String lastName);
    }
}
