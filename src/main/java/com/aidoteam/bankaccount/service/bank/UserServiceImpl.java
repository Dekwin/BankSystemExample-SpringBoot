package com.aidoteam.bankaccount.service.bank;

import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> findAllByFirstName(String firstName) {
        return userRepository.findAllByFirstName(firstName);
    }

    @Override
    public void create(String firstName, String lastName, String email, String password, String phone) {
        UserEntity foundUser = userRepository.findByEmail(email);
        if (foundUser == null) {
            UserEntity userEntity = new UserEntity(firstName, lastName, email, password, phone);
            userRepository.save(userEntity);
        }else{
            throw new IllegalArgumentException("User with this email already exists.");
        }
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
