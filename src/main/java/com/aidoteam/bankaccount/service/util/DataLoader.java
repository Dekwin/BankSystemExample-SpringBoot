package com.aidoteam.bankaccount.service.util;

import com.aidoteam.bankaccount.model.*;
import com.aidoteam.bankaccount.repository.IncomeTypeRepository;
import com.aidoteam.bankaccount.repository.OutcomeTypeRepository;
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
    private IncomeTypeRepository incomeTypeRepository;
    private OutcomeTypeRepository outcomeTypeRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, WalletRepository walletRepository, IncomeTypeRepository incomeTypeRepository, OutcomeTypeRepository outcomeTypeRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.incomeTypeRepository = incomeTypeRepository;
        this.outcomeTypeRepository = outcomeTypeRepository;
    }

    public void run(ApplicationArguments args) {

        UserEntity igorEntity = new UserEntity("igor", "kasoi", "dekstersniper@gmail.com","1234","+3809533333");

        List<WalletEntity> walletEntities = new ArrayList<>();

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setOwner(igorEntity);
        walletEntity.setAmount(new Long(2000));
        walletEntity.setAccount("acc1");
        walletEntity.setCreatedAt(System.currentTimeMillis());
        walletEntity.setUpdatedAt(System.currentTimeMillis());
        walletEntity.setCurrency(Currency.USD);


        walletEntities.add(walletEntity);

        igorEntity.setWallets(walletEntities);

        userRepository.save(igorEntity);

        walletRepository.save(walletEntity);


        UserEntity denisEntity = new UserEntity("denis", "lis", "den@gmail.com","1234","+380958833");

        List<WalletEntity> denisWalletEntities = new ArrayList<>();

        WalletEntity denisWalletEntity = new WalletEntity();
        denisWalletEntity.setOwner(denisEntity);
        denisWalletEntity.setAmount(new Long(33000));
        denisWalletEntity.setAccount("acc2");
        denisWalletEntity.setCreatedAt(System.currentTimeMillis());
        denisWalletEntity.setUpdatedAt(System.currentTimeMillis());
        denisWalletEntity.setCurrency(Currency.UAH);


        denisWalletEntities.add(denisWalletEntity);
        denisEntity.setWallets(denisWalletEntities);



        userRepository.save(denisEntity);

        walletRepository.save(denisWalletEntity);


        //incomeTypes
        IncomeTypeEntity incomeTypeEntity = new IncomeTypeEntity();
        incomeTypeEntity.setTitle("Пенсия");
        incomeTypeEntity.setDescription("пенсия месяц");

        IncomeTypeEntity incomeTypeEntity1 = new IncomeTypeEntity();
        incomeTypeEntity1.setTitle("Зарплата");
        incomeTypeEntity1.setDescription("ЗП месяц");

        incomeTypeRepository.save(incomeTypeEntity);
        incomeTypeRepository.save(incomeTypeEntity1);

        //outcomeTypes
        OutcomeTypeEntity outcomeTypeEntity = new OutcomeTypeEntity();
        outcomeTypeEntity.setTitle("Налог 1");
        outcomeTypeEntity.setDescription("налог на жилье");

        OutcomeTypeEntity outcomeTypeEntity1 = new OutcomeTypeEntity();
        outcomeTypeEntity1.setTitle("Налог 2");
        outcomeTypeEntity1.setDescription("налог на авто");

        outcomeTypeRepository.save(outcomeTypeEntity);
        outcomeTypeRepository.save(outcomeTypeEntity1);


        //incomes
//        IncomeEntity incomeEntity = new IncomeEntity();
//        incomeEntity.setDatetime(System.currentTimeMillis());
//        incomeEntity.setAmount(new Long(127));
//        incomeEntity.setAccountNumber("acc1");
//        incomeEntity.set




    }
}