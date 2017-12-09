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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WalletEntity getSenderWallet() {
        return senderWallet;
    }

    public void setSenderWallet(WalletEntity senderWallet) {
        this.senderWallet = senderWallet;
    }

    public WalletEntity getRecipientWallet() {
        return recipientWallet;
    }

    public void setRecipientWallet(WalletEntity recipientWallet) {
        this.recipientWallet = recipientWallet;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public TransferTransactionEntity() {
    }
}
