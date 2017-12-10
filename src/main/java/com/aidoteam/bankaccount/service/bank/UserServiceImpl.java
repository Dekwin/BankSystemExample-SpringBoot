package com.aidoteam.bankaccount.service.bank;

import com.aidoteam.bankaccount.model.Currency;
import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.model.WalletEntity;
import com.aidoteam.bankaccount.repository.UserRepository;
import com.aidoteam.bankaccount.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;

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
            List<WalletEntity> walletEntities = new ArrayList<>();


            WalletEntity usdWalletEntity = new WalletEntity();
            usdWalletEntity.setOwner(userEntity);
            usdWalletEntity.setAmount(new Long(100));
            usdWalletEntity.setAccount(userEntity.getEmail()+Currency.USD);
            usdWalletEntity.setCreatedAt(System.currentTimeMillis());
            usdWalletEntity.setUpdatedAt(System.currentTimeMillis());
            usdWalletEntity.setCurrency(Currency.USD);
            walletEntities.add(usdWalletEntity);

            WalletEntity uahWalletEntity = new WalletEntity();
            uahWalletEntity.setOwner(userEntity);
            uahWalletEntity.setAmount(new Long(1000));
            uahWalletEntity.setAccount(userEntity.getEmail()+Currency.UAH);
            uahWalletEntity.setCreatedAt(System.currentTimeMillis());
            uahWalletEntity.setUpdatedAt(System.currentTimeMillis());
            uahWalletEntity.setCurrency(Currency.UAH);
            walletEntities.add(uahWalletEntity);
            userEntity.setWallets(walletEntities);


            userRepository.save(userEntity);

            walletRepository.save(usdWalletEntity);
            walletRepository.save(uahWalletEntity);



        }else{
            throw new IllegalArgumentException("User with this email already exists.");
        }
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserEntity> findByFirstNameOrEmail(String firstName, String email) {
        List<UserEntity> userEntities = new ArrayList<>();
        if (firstName != null) {
            userEntities.addAll(userRepository.findByFirstNameIgnoreCaseContaining(firstName));
        }
        if(email != null){
            userEntities.addAll(userRepository.findByEmailIgnoreCaseContaining(email));
        }
        return  userEntities;
    }
}
