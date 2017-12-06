package com.aidoteam.bankaccount.model;

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
    private WalletEntity wallet;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INCOME_TYPE_ID")
    private IncomeTypeEntity incomeType;


    public IncomeEntity() {
    }
}
