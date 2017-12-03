package com.aidoteam.bankaccount.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OutcomeEntity")
public class OutcomeEntity {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;

    @ManyToMany(mappedBy="outcomes")
    private List<WalletEntity> wallets;

    public OutcomeEntity() {
    }
}
