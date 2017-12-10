package com.aidoteam.bankaccount.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WalletEntity")
public class WalletEntity {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OWNER_ID")
    @JsonBackReference
    private UserEntity owner;

    @Column(nullable = false)
    private Long amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(nullable = false)
    private Long createdAt;
    @Column(nullable = false)
    private Long updatedAt;
    @Column(nullable = false)
    private String account;

    @OneToMany(mappedBy="senderWallet")
    @JsonManagedReference
    private List<TransferTransactionEntity> sendTransactions;

    @OneToMany(mappedBy="recipientWallet")
    @JsonManagedReference
    private List<TransferTransactionEntity> receiveTransactions;

    @OneToMany(mappedBy="wallet")
    @JsonManagedReference
    private List<IncomeEntity> incomes;

    @OneToMany(mappedBy="wallet")
    @JsonManagedReference
    private List<OutcomeEntity> outcomes;

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAccount(String account) {
        this.account = account;
    }



    public Long getId() {
        return id;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public Long getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public String getAccount() {
        return account;
    }

    public List<TransferTransactionEntity> getSendTransactions() {
        return sendTransactions;
    }

    public List<TransferTransactionEntity> getReceiveTransactions() {
        return receiveTransactions;
    }

    public List<IncomeEntity> getIncomes() {
        return incomes;
    }

    public List<OutcomeEntity> getOutcomes() {
        return outcomes;
    }

    public void setSendTransactions(List<TransferTransactionEntity> sendTransactions) {
        this.sendTransactions = sendTransactions;
    }

    public void setReceiveTransactions(List<TransferTransactionEntity> receiveTransactions) {
        this.receiveTransactions = receiveTransactions;
    }

    public void setIncomes(List<IncomeEntity> incomes) {
        this.incomes = incomes;
    }

    public void setOutcomes(List<OutcomeEntity> outcomes) {
        this.outcomes = outcomes;
    }

    public WalletEntity(UserEntity owner, Long amount, Currency currency, Long createdAt, Long updatedAt, String account, List<TransferTransactionEntity> sendTransactions, List<TransferTransactionEntity> receiveTransactions, List<IncomeEntity> incomes, List<OutcomeEntity> outcomes) {
        this.owner = owner;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.account = account;
        this.sendTransactions = sendTransactions;
        this.receiveTransactions = receiveTransactions;
        this.incomes = incomes;
        this.outcomes = outcomes;
    }

    //    @ManyToMany
//    @JoinTable(
//            name="WALLET_INCOME",
//            joinColumns=@JoinColumn(name="WALLET_ID", referencedColumnName="ID"),
//            inverseJoinColumns=@JoinColumn(name="INCOME_ID", referencedColumnName="ID"))
//    private List<IncomeEntity> incomes;
//
//    @ManyToMany
//    @JoinTable(
//            name="WALLET_OUTCOME",
//            joinColumns=@JoinColumn(name="WALLET_ID", referencedColumnName="ID"),
//            inverseJoinColumns=@JoinColumn(name="OUTCOME_ID", referencedColumnName="ID"))
//    private List<OutcomeEntity> outcomes;

    public WalletEntity() {
    }


}
