package com.aidoteam.bankaccount.model;


import javax.persistence.*;

@Entity
@Table(name = "TransferTransactionEntity")
public class TransferTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SENDER_WALLET_ID")
    private WalletEntity senderWallet;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RECIPIENT_WALLET_ID")
    private WalletEntity recipientWallet;

    @Column(nullable = false)
    private Long amount;
    @Column(nullable = false)
    private Long datetime;
    @Column(nullable = false)
    private String description;

    public TransferTransactionEntity() {
    }
}
