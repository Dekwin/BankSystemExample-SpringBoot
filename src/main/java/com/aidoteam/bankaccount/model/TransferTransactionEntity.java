package com.aidoteam.bankaccount.model;


import javax.persistence.*;

@Entity
@Table(name = "TransferTransactionEntity")
public class TransferTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SENDER_ID")
    private UserEntity sender;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RECIPIENT_ID")
    private UserEntity recipient;

    @Column(nullable = false)
    private Long amount;
    @Column(nullable = false)
    private Long datetime;
    @Column(nullable = false)
    private String description;

    public TransferTransactionEntity() {
    }
}
