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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OutcomeEntity> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<OutcomeEntity> outcomes) {
        this.outcomes = outcomes;
    }

    public OutcomeTypeEntity() {
    }
}