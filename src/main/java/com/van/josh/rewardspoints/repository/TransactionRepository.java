package com.van.josh.rewardspoints.repository;

import com.van.josh.rewardspoints.model.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findByCustomerId(Long customerId);

    List<Transaction> findByCustomerIdAndAmountBetween(Long customerId, BigDecimal minAmount, BigDecimal maxAmount);

    List<Transaction> findByCustomerIdAndTransactionDateBetween(Long customerId, LocalDate startDate, LocalDate endDate);

    List<Transaction> findByCustomerIdAndTransactionType(Long customerId, String transactionType);

    List<Transaction> findByCustomerId(Long customerId, Sort sort);
}

