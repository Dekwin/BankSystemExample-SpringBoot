package com.aidoteam.bankaccount.controller.bank;

import com.aidoteam.bankaccount.model.*;
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
    ResponseEntity<TransferTransactionEntity> makeTransferTransaction(@PathVariable Long fromWalletId,
                                                                            @JsonProperty("toWalletId") Long toWalletId,
                                                                            @JsonProperty("toAccount") String toAccount,
                                                                            @JsonProperty("amount") Long amount,
                                                                            @JsonProperty("description") String description) {
        TransferTransactionEntity transferTransactionEntity = walletService.makeTransferTransaction(fromWalletId,toWalletId, toAccount, amount,description);
        return new ResponseEntity(transferTransactionEntity,HttpStatus.OK);
    }

    @PostMapping("/{fromWalletId}/payment")
    ResponseEntity<OutcomeEntity> makePayment(@PathVariable Long fromWalletId,
                                              @JsonProperty("toAccount") String toAccount,
                                              @JsonProperty("amount") Long amount,
                                              @JsonProperty("outcomeTypeId") Long outcomeTypeId,
                                              @JsonProperty("description") String description) throws IllegalAccessException {
        OutcomeEntity outcomeEntity = walletService.makeOutcomePayment(fromWalletId,toAccount,amount,outcomeTypeId,description);
        return new ResponseEntity(outcomeEntity, HttpStatus.OK);
    }

    @GetMapping("/{walletId}/outcomes")
    ResponseEntity<List<OutcomeEntity>> getOutcomesBetweenDates(@PathVariable Long walletId, @RequestParam("dateFrom") Long dateFrom, @RequestParam("dateTo") Long dateTo) throws IllegalAccessException {
        return new ResponseEntity<>(walletService.findOutcomesByWalletIdBetweenDates(walletId,dateFrom,dateTo), HttpStatus.OK);
    }
    @GetMapping("/{walletId}/outcomes/{outcomeTypeId}")
    ResponseEntity<List<OutcomeEntity>> getOutcomesByOutcomeTypeIdBetweenDates(@PathVariable Long walletId, @PathVariable Long outcomeTypeId, @RequestParam("dateFrom") Long dateFrom, @RequestParam("dateTo") Long dateTo) throws IllegalAccessException {
        return new ResponseEntity<>(walletService.findOutcomesByWalletIdAndOutcomeTypeIdBetweenDates(walletId,outcomeTypeId,dateFrom,dateTo), HttpStatus.OK);
    }



    @GetMapping("/outcome-types")
    ResponseEntity<List<OutcomeTypeEntity>> getOutcomeTypes() throws IllegalAccessException {
        return new ResponseEntity<>(walletService.getAllOutcomeTypes(), HttpStatus.OK);
    }

    @GetMapping("/income-types")
    ResponseEntity<List<IncomeTypeEntity>> getIncomeTypes() throws IllegalAccessException {
        return new ResponseEntity<>(walletService.getAllIncomeTypes(), HttpStatus.OK);
    }






}
