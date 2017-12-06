package com.aidoteam.bankaccount.controller.bank;

import com.aidoteam.bankaccount.model.WalletEntity;
import com.aidoteam.bankaccount.service.bank.IncomeService;
import com.aidoteam.bankaccount.service.bank.UserService;
import com.aidoteam.bankaccount.service.bank.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    WalletService walletService;

    @Autowired
    IncomeService incomeService;

    @Autowired
    UserService userService;


    @RequestMapping("/")
    String getWallets() {
        return "Hello!";
    }

    @RequestMapping("/{walletId}")
    String getWalletById(@PathVariable String walletId) {
        return "Hello!";
    }
}
