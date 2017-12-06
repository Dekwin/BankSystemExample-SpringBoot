package com.aidoteam.bankaccount.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "IncomeTypeEntity")
public class IncomeTypeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;

    @OneToMany(mappedBy="incomeType")
    private List<IncomeEntity> incomes;

    public IncomeTypeEntity() {
    }
}
