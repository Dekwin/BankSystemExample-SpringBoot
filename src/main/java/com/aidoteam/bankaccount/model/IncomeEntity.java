package com.aidoteam.bankaccount.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "IncomeEntity")
public class IncomeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long amount;
    @Column
    private String accountNumber;
    @Column
    private Long datetime;
    @Column
    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WALLET_ID")
    @JsonBackReference
    private WalletEntity wallet;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INCOME_TYPE_ID")
    @JsonBackReference
    private IncomeTypeEntity incomeType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getDatetime() {
        return datetime;
    }

    public void setDatetime(Long datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WalletEntity getWallet() {
        return wallet;
    }

    public void setWallet(WalletEntity wallet) {
        this.wallet = wallet;
    }

    public IncomeTypeEntity getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(IncomeTypeEntity incomeType) {
        this.incomeType = incomeType;
    }

    public IncomeEntity() {
    }
}
