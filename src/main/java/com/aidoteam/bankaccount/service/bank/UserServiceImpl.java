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
    public List<UserEntity> findAllByFirstName(String firstName) {
        return userRepository.findAllByFirstName(firstName);
    }
}
