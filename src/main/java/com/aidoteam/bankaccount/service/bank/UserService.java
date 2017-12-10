package com.aidoteam.bankaccount.service.bank;

import com.aidoteam.bankaccount.model.UserEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public interface UserService {
    void save(UserEntity userEntity);
    List<UserEntity> findAllByFirstName(String firstName);
    void create(String firstName,String lastName,String email,String password,String phone);
    UserEntity findByEmail(String email);
    List<UserEntity> findByFirstNameOrEmail(String firstName, String email);
}
