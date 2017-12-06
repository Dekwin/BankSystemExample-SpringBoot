package com.aidoteam.bankaccount.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OutcomeTypeEntity")
public class OutcomeTypeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;

    @OneToMany(mappedBy="outcomeType")
    private List<OutcomeEntity> outcomes;

    public OutcomeTypeEntity() {
    }
}