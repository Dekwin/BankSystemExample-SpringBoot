package com.aidoteam.bankaccount.service.bank;

import com.aidoteam.bankaccount.model.OutcomeEntity;
import com.aidoteam.bankaccount.model.OutcomeTypeEntity;
import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.model.WalletEntity;
import com.aidoteam.bankaccount.repository.OutcomeRepository;
import com.aidoteam.bankaccount.repository.OutcomeTypeRepository;
import com.aidoteam.bankaccount.repository.UserRepository;
import com.aidoteam.bankaccount.repository.WalletRepository;
import com.aidoteam.bankaccount.service.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OutcomeServiceImpl implements OutcomeService{
    @Autowired
    private OutcomeRepository outcomeRepository;
    @Autowired
    private OutcomeTypeRepository outcomeTypeRepository;
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<OutcomeEntity> findByWalletIdBetweenDates(Long walletId, Long datetimeFrom, Long datetimeTo) {
        return outcomeRepository.findByWalletIdBetweenDates(walletId,datetimeFrom,datetimeTo);
    }

    @Override
    public List<OutcomeEntity> findByWalletIdAndOutcomeTypeIdBetweenDates(Long walletId, Long outcomeTypeId, Long datetimeFrom, Long datetimeTo) {
       return outcomeRepository.findByWalletIdAndOutcomeTypeIdBetweenDates(walletId,outcomeTypeId,datetimeFrom,datetimeTo);
    }

    @Override
    public void create(Long walletId, String toAccount,Long amount,Long outcomeTypeId,String description) throws IllegalAccessException {
        if (toAccount != null && !toAccount.equals("") && amount != null && amount > 0 && outcomeTypeId != null && description != null&& walletId != null) {
            OutcomeTypeEntity outcomeTypeEntity = outcomeTypeRepository.findById(outcomeTypeId);
            WalletEntity walletEntity = walletRepository.findByIdAndOwner(walletId,getMe());
            if(outcomeTypeEntity != null && walletEntity != null) {
                Long newWalletAmount = walletEntity.getAmount() - amount;

                OutcomeEntity outcomeEntity = new OutcomeEntity();
                outcomeEntity.setDatetime(System.currentTimeMillis());
                outcomeEntity.setAmount(amount);
                outcomeEntity.setAccountNumber(toAccount);
                outcomeEntity.setDescription(description);
                outcomeEntity.setOutcomeType(outcomeTypeEntity);
                outcomeEntity.setWallet(walletEntity);

                walletEntity.setAmount(newWalletAmount);
                outcomeRepository.save(outcomeEntity);
                walletRepository.save(walletEntity);

            }else{
                throw new IllegalAccessException("Wrong outcome type id");
            }
        }else{
            throw new IllegalArgumentException("Wrong input parameters");
        }
    }

    private UserEntity getMe(){
        String email = (String)authenticationFacade.getAuthentication().getPrincipal();
        UserEntity owner = userRepository.findByEmail(email);
        return  owner;
    }

}
