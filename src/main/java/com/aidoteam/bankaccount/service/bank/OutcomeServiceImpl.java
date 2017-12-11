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


    @Override
    public List<OutcomeEntity> findByWalletIdBetweenDates(Long walletId, Long datetimeFrom, Long datetimeTo) {
        return outcomeRepository.findByWalletIdBetweenDates(walletId,datetimeFrom,datetimeTo);
    }

    @Override
    public List<OutcomeEntity> findByWalletIdAndOutcomeTypeIdBetweenDates(Long walletId, Long outcomeTypeId, Long datetimeFrom, Long datetimeTo) {
       return outcomeRepository.findByWalletIdAndOutcomeTypeIdBetweenDates(walletId,outcomeTypeId,datetimeFrom,datetimeTo);
    }


}
