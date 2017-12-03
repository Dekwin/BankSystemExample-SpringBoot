package com.aidoteam.bankaccount.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "UserEntity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    private String password;
    private String phone;

    @OneToMany(mappedBy="owner")
    private List<WalletEntity> wallets;

    @OneToMany(mappedBy="sender")
    private List<TransferTransactionEntity> sendTransactions;

    @OneToMany(mappedBy="recipient")
    private List<TransferTransactionEntity> receiveTransactions;

    protected UserEntity() {}

    public UserEntity(String firstName, String lastName, String email, String password, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format(
                "UserEntity[id=%d, firstName='%s', lastName='%s', email='%s', password='%s', phone='%s']",
                id, firstName, lastName, email, password, phone);
    }

}