package com.aidoteam.bankaccount.service.bank;

import com.aidoteam.bankaccount.model.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAllByFirstName(String firstName);
}
