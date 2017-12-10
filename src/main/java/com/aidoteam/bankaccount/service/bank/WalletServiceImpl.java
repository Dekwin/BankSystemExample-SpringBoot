package com.aidoteam.bankaccount.service.bank;

import com.aidoteam.bankaccount.model.IncomeEntity;
import com.aidoteam.bankaccount.model.TransferTransactionEntity;
import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.model.WalletEntity;
import com.aidoteam.bankaccount.repository.IncomeRepository;
import com.aidoteam.bankaccount.repository.TransferTransactionRepository;
import com.aidoteam.bankaccount.repository.UserRepository;
import com.aidoteam.bankaccount.repository.WalletRepository;
import com.aidoteam.bankaccount.service.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private TransferTransactionRepository transferTransactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Override
    public WalletEntity findById(Long id) {
        return walletRepository.findById(id);
    }

    @Override
    public List<WalletEntity> getOwnWallets() {
        UserEntity owner = getMe();
        return walletRepository.findByOwner(owner);
    }

    @Override
    public void makeTransferTransaction(Long fromWalletId, Long toWalletId, String toAccount, Long amount, String description) {

        UserEntity userEntity = getMe(); //current user

        WalletEntity walletFrom = walletRepository.findByIdAndOwner(fromWalletId,userEntity);
        WalletEntity walletTo = null;
        if (toWalletId != null) {
            walletTo = walletRepository.findById(toWalletId);
        }else{
            walletTo = walletRepository.findByAccount(toAccount);
        }

        if (walletFrom != null && walletTo != null && amount != null && amount > 0 && walletFrom.getCurrency() == walletTo.getCurrency()){
            if(walletFrom.getAmount() >= amount){

                Long newFromAmount = walletFrom.getAmount() - amount;
                Long newToAmount = walletTo.getAmount() + amount;
                TransferTransactionEntity newTransaction = new TransferTransactionEntity();


                newTransaction.setSenderWallet(walletFrom);
                newTransaction.setRecipientWallet(walletTo);
                newTransaction.setAmount(amount);
                newTransaction.setDatetime(System.currentTimeMillis());
                newTransaction.setDescription(description);

                transferTransactionRepository.save(newTransaction);

                walletFrom.setAmount(newFromAmount);
                walletRepository.save(walletFrom);

                walletTo.setAmount(newToAmount);
                walletRepository.save(walletTo);


            }else{
                throw new IllegalArgumentException("Wrong transfer params.");
            }
        }else{
            throw new IllegalArgumentException("Wrong transfer params.");
        }

    }

    @Override
    public List<TransferTransactionEntity> findTransferTransactionsBetweenDates(Long walletId, Long dateFrom, Long dateTo) {
        UserEntity userEntity = getMe();
        WalletEntity walletEntity = walletRepository.findByIdAndOwner(walletId,userEntity);

        List<TransferTransactionEntity> sentT = transferTransactionRepository.findBySenderWalletAndDatetimeBetween(walletEntity,dateFrom,dateTo);
        List<TransferTransactionEntity> receivedT = transferTransactionRepository.findByRecipientWalletAndDatetimeBetween(walletEntity,dateFrom,dateTo);

        List<TransferTransactionEntity> allT = new ArrayList<>();
        allT.addAll(sentT);
        allT.addAll(receivedT);

        return allT;
    }

    @Override
    public List<IncomeEntity> findIncomesByWalletIdAndIncomeTypeIdBetweenDates(Long walletId, Long incomeTypeId, Long datetimeFrom, Long datetimeTo) throws IllegalAccessException {
        UserEntity owner = getMe();
        WalletEntity walletEntity = walletRepository.findByIdAndOwner(walletId,owner);
        if (walletEntity != null) {
          return   incomeRepository.findByWalletIdAndIncomeTypeIdBetweenDates(walletEntity.getId(), incomeTypeId, datetimeFrom, datetimeTo);
        }else{
            throw new IllegalAccessException("No permission");
        }

    }

    @Override
    public List<IncomeEntity> findIncomesByWalletIdBetweenDates(Long walletId, Long datetimeFrom, Long datetimeTo) throws IllegalAccessException {
        UserEntity owner = getMe();
        WalletEntity walletEntity = walletRepository.findByIdAndOwner(walletId,owner);
        if (walletEntity != null) {
            return   incomeRepository.findByWalletIdBetweenDates(walletEntity.getId(), datetimeFrom, datetimeTo);
        }else{
            throw new IllegalAccessException("No permission");
        }
    }

    private UserEntity getMe(){
        String email = (String)authenticationFacade.getAuthentication().getPrincipal();
        UserEntity owner = userRepository.findByEmail(email);
        return  owner;
    }


}
