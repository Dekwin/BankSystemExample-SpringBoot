package com.aidoteam.bankaccount.controller.bank;

import com.aidoteam.bankaccount.model.IncomeEntity;
import com.aidoteam.bankaccount.model.TransferTransactionEntity;
import com.aidoteam.bankaccount.model.WalletEntity;
import com.aidoteam.bankaccount.service.bank.IncomeService;
import com.aidoteam.bankaccount.service.bank.UserService;
import com.aidoteam.bankaccount.service.bank.WalletService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    WalletService walletService;

    @Autowired
    IncomeService incomeService;

    @Autowired
    UserService userService;


    @GetMapping("")
    ResponseEntity<List<WalletEntity>> getWallets() {
        return new ResponseEntity<>(walletService.getOwnWallets(), HttpStatus.OK);
    }

    @GetMapping("/{walletId}")
    ResponseEntity<WalletEntity> getWalletById(@PathVariable Long walletId) {
        return new ResponseEntity<>(walletService.findById(walletId), HttpStatus.OK);
    }

    @GetMapping("/{walletId}/incomes")
    ResponseEntity<List<IncomeEntity>> getIncomesBetweenDates(@PathVariable Long walletId, @RequestParam("dateFrom") Long dateFrom, @RequestParam("dateTo") Long dateTo) throws IllegalAccessException {
        return new ResponseEntity<>(walletService.findIncomesByWalletIdBetweenDates(walletId,dateFrom,dateTo), HttpStatus.OK);
    }
    @GetMapping("/{walletId}/incomes/{incomeTypeId}")
    ResponseEntity<List<IncomeEntity>> getIncomesByIncomeTypeIdBetweenDates(@PathVariable Long walletId, @PathVariable Long incomeTypeId, @RequestParam("dateFrom") Long dateFrom, @RequestParam("dateTo") Long dateTo) throws IllegalAccessException {
        return new ResponseEntity<>(walletService.findIncomesByWalletIdAndIncomeTypeIdBetweenDates(walletId,incomeTypeId,dateFrom,dateTo), HttpStatus.OK);
    }


    @GetMapping("/{walletId}/transfer-transactions")
    ResponseEntity<List<TransferTransactionEntity>> getTransferTransactionsBetweenDates(@PathVariable Long walletId, @RequestParam("dateFrom") Long dateFrom, @RequestParam("dateTo") Long dateTo) {
        return new ResponseEntity<>(walletService.findTransferTransactionsBetweenDates(walletId,dateFrom,dateTo), HttpStatus.OK);
    }


    @PostMapping("/{fromWalletId}/transfer")
    ResponseEntity makeTransferTransaction(@PathVariable Long fromWalletId,
                                                                            @JsonProperty("toWalletId") Long toWalletId,
                                                                            @JsonProperty("toAccount") String toAccount,
                                                                            @JsonProperty("amount") Long amount,
                                                                            @JsonProperty("description") String description) {
        walletService.makeTransferTransaction(fromWalletId,toWalletId, toAccount, amount,description);
        return new ResponseEntity(HttpStatus.OK);
    }



}
