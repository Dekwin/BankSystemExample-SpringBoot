package com.aidoteam.bankaccount.service.bank;

import com.aidoteam.bankaccount.model.*;
import com.aidoteam.bankaccount.repository.*;
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
    private OutcomeTypeRepository outcomeTypeRepository;
    @Autowired
    private OutcomeRepository outcomeRepository;
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    @Autowired
    private IncomeTypeRepository incomeTypeRepository;

    @Override
    public WalletEntity findById(Long id) {
        return walletRepository.findById(id);
    }

    @Override
    public List<WalletEntity> getOwnWallets() {
        UserEntity owner = authenticationFacade.getCurrentUser();
        return walletRepository.findByOwner(owner);
    }

    @Override
    public TransferTransactionEntity makeTransferTransaction(Long fromWalletId, Long toWalletId, String toAccount, Long amount, String description) {

        UserEntity userEntity = authenticationFacade.getCurrentUser(); //current user

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
                return newTransaction;


            }else{
                throw new IllegalArgumentException("Wrong transfer params.");
            }
        }else{
            throw new IllegalArgumentException("Wrong transfer params.");
        }

    }

    @Override
    public List<TransferTransactionEntity> findTransferTransactionsBetweenDates(Long walletId, Long dateFrom, Long dateTo) {
        UserEntity userEntity = authenticationFacade.getCurrentUser();
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
        UserEntity owner = authenticationFacade.getCurrentUser();
        WalletEntity walletEntity = walletRepository.findByIdAndOwner(walletId,owner);
        if (walletEntity != null) {
          return   incomeRepository.findByWalletIdAndIncomeTypeIdBetweenDates(walletEntity.getId(), incomeTypeId, datetimeFrom, datetimeTo);
        }else{
            throw new IllegalAccessException("No permission");
        }

    }

    @Override
    public List<OutcomeEntity> findOutcomesByWalletIdAndOutcomeTypeIdBetweenDates(Long walletId, Long outcomeTypeId, Long datetimeFrom, Long datetimeTo) throws IllegalAccessException {
        UserEntity owner = authenticationFacade.getCurrentUser();
        WalletEntity walletEntity = walletRepository.findByIdAndOwner(walletId,owner);
        if (walletEntity != null) {
            return   outcomeRepository.findByWalletIdAndOutcomeTypeIdBetweenDates(walletEntity.getId(), outcomeTypeId, datetimeFrom, datetimeTo);
        }else{
            throw new IllegalAccessException("No permission");
        }
    }

    @Override
    public List<IncomeEntity> findIncomesByWalletIdBetweenDates(Long walletId, Long datetimeFrom, Long datetimeTo) throws IllegalAccessException {
        UserEntity owner = authenticationFacade.getCurrentUser();
        WalletEntity walletEntity = walletRepository.findByIdAndOwner(walletId,owner);
        if (walletEntity != null) {
            return   incomeRepository.findByWalletIdBetweenDates(walletEntity.getId(), datetimeFrom, datetimeTo);
        }else{
            throw new IllegalAccessException("No permission");
        }
    }

    @Override
    public List<OutcomeEntity> findOutcomesByWalletIdBetweenDates(Long walletId, Long datetimeFrom, Long datetimeTo) throws IllegalAccessException {
        UserEntity owner = authenticationFacade.getCurrentUser();
        WalletEntity walletEntity = walletRepository.findByIdAndOwner(walletId,owner);
        if (walletEntity != null) {
            return   outcomeRepository.findByWalletIdBetweenDates(walletEntity.getId(), datetimeFrom, datetimeTo);
        }else{
            throw new IllegalAccessException("No permission");
        }
    }

    @Override
    public OutcomeEntity makeOutcomePayment(Long walletId, String toAccount,Long amount,Long outcomeTypeId,String description) throws IllegalAccessException {
        if (toAccount != null && !toAccount.equals("") && amount != null && amount > 0 && outcomeTypeId != null && description != null&& walletId != null) {
            OutcomeTypeEntity outcomeTypeEntity = outcomeTypeRepository.findById(outcomeTypeId);
            WalletEntity walletEntity = walletRepository.findByIdAndOwner(walletId,authenticationFacade.getCurrentUser());
            if(outcomeTypeEntity != null && walletEntity != null ) {
                if(walletEntity.getAmount() > 0 && (walletEntity.getAmount() > amount)) {
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
                    return outcomeEntity;
                }else{
                    throw new IllegalAccessException("Your balance is lower than amount");
                }
            }else{
                throw new IllegalAccessException("Wrong outcome type id");
            }
        }else{
            throw new IllegalArgumentException("Wrong input parameters");
        }
    }

    @Override
    public List<OutcomeTypeEntity> getAllOutcomeTypes() {
        List<OutcomeTypeEntity> outcomeTypeEntities = outcomeTypeRepository.findAll();
        return outcomeTypeEntities;
    }

    @Override
    public List<IncomeTypeEntity> getAllIncomeTypes() {
        List<IncomeTypeEntity> incomeTypeEntities = incomeTypeRepository.findAll();
        return incomeTypeEntities;
    }




}
