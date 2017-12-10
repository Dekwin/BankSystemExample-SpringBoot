package com.aidoteam.bankaccount.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "OutcomeEntity")
public class OutcomeEntity {
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
    @JoinColumn(name="OUTCOME_TYPE_ID")
    @JsonBackReference
    private OutcomeTypeEntity outcomeType;

    public OutcomeEntity() {
    }
}
