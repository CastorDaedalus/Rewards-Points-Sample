package com.van.josh.rewardspoints.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Table("CUSTOMER")
public class Customer {

    @Id
    @Column(value = "ID")
    private Long id;

    private String name;

    private String email;

    @Transient
    private List<Transaction> transaction;

    @Transient
    private List<RewardsPoints> rewardsPoints;

    public Customer() {

    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

