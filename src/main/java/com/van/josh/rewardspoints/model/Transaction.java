package com.van.josh.rewardspoints.model;

import com.van.josh.rewardspoints.Constants.TransactionType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Table("TRANSACTION")
public class Transaction {

    @Id
    @Column(value = "ID")
    private Long id;

    private BigDecimal amount;

    private LocalDate transactionDate;

    private TransactionType transactionType;

    private Long customerId;

    public Transaction() {

    }

    public Transaction(BigDecimal amount, LocalDate transactionDate, TransactionType transactionType) {
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
    }
}

