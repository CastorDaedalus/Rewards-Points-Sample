package com.van.josh.rewardspoints.service;

import com.van.josh.rewardspoints.model.Transaction;
import com.van.josh.rewardspoints.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RewardsPointsService rewardsPointsService;

    public Transaction createTransaction(Transaction transaction) {

        transactionRepository.save(transaction);

        rewardsPointsService.createRewardsEntry(transaction);

        return transaction;
    }

    public void saveAll(List<Transaction> transactions) {
        transactionRepository.saveAll(transactions);

        rewardsPointsService.createRewardsEntries(transactions);
    }

    public List<Transaction> getTransactionsByCustomerId(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    public List<Transaction> getTransactionsByCustomerIdAndTransactionDateBetween(Long customerId, int days) {
        LocalDate currentDate = LocalDate.now();
        LocalDate pastDate = currentDate.minusDays(days);
        return transactionRepository.findByCustomerIdAndTransactionDateBetween(customerId, pastDate, currentDate);
    }
}
