package com.aidoteam.bankaccount.service.bank;

import com.aidoteam.bankaccount.model.TransferTransactionEntity;
import com.aidoteam.bankaccount.model.UserEntity;
import com.aidoteam.bankaccount.model.WalletEntity;
import com.aidoteam.bankaccount.repository.TransferTransactionRepository;
import com.aidoteam.bankaccount.repository.UserRepository;
import com.aidoteam.bankaccount.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private TransferTransactionRepository transferTransactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public void makeTransferTransaction(Long fromWalletId, Long toWalletId, String toAccount, Long amount, String description) {

        UserEntity userEntity = null; //current user

        WalletEntity walletFrom = walletRepository.findByIdAndOwner(fromWalletId,userEntity);
        WalletEntity walletTo = walletRepository.findById(toWalletId);

        if (walletFrom != null && walletTo != null && amount != null && amount > 0){
            if(walletFrom.getAmount() >= amount){

                TransferTransactionEntity newTransaction = new TransferTransactionEntity();

                newTransaction.setSenderWallet(walletFrom);
                newTransaction.setRecipientWallet(walletTo);
                newTransaction.setAmount(amount);
                newTransaction.setDatetime(System.currentTimeMillis());
                newTransaction.setDescription(description);

                transferTransactionRepository.save(newTransaction);

            }else{
                throw new IllegalArgumentException("Wrong transfer params.");
            }
        }else{
            throw new IllegalArgumentException("Wrong transfer params.");
        }




    }




}
