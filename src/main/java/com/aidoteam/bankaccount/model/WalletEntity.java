package com.aidoteam.bankaccount.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WalletEntity")
public class WalletEntity {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OWNER_ID")
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

    @ManyToMany
    @JoinTable(
            name="WALLET_INCOME",
            joinColumns=@JoinColumn(name="WALLET_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="INCOME_ID", referencedColumnName="ID"))
    private List<IncomeEntity> incomes;

    @ManyToMany
    @JoinTable(
            name="WALLET_OUTCOME",
            joinColumns=@JoinColumn(name="WALLET_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="OUTCOME_ID", referencedColumnName="ID"))
    private List<OutcomeEntity> outcomes;

    public WalletEntity() {
    }

    public WalletEntity(UserEntity owner, Long amount, Currency currency, Long createdAt, Long updatedAt, String account, List<IncomeEntity> incomes, List<OutcomeEntity> outcomes) {
        this.owner = owner;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.account = account;
        this.incomes = incomes;
        this.outcomes = outcomes;
    }
}
