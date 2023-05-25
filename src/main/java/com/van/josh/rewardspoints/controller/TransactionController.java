package com.van.josh.rewardspoints.controller;

import com.van.josh.rewardspoints.model.Transaction;
import com.van.josh.rewardspoints.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/previous-days/{days}")
    public ResponseEntity<List<Transaction>> getPreviousDaysTransactions(@PathVariable int days, @RequestParam("customerId") Long customerId) {
        return ResponseEntity.ok(transactionService.getTransactionsByCustomerIdAndTransactionDateBetween(customerId, days));
    }
}
