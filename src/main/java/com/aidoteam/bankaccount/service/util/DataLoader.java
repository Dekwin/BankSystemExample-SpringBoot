package com.aidoteam.bankaccount.service.util;

import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.model.WalletEntity;
import com.aidoteam.bankaccount.repository.UserRepository;
import com.aidoteam.bankaccount.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private WalletRepository walletRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }



    public void run(ApplicationArguments args) {

        UserEntity userEntity = new UserEntity("igor", "kasoi", "dekstersniper@gmail.com","1234","+3809533333");

        List<WalletEntity> walletEntities = new ArrayList<>();

        walletEntities.add(new WalletEntity());


        userRepository.save(userEntity);





    }
}