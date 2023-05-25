package com.van.josh.rewardspoints.controller;

import com.van.josh.rewardspoints.service.RewardsPointsService;
import com.van.josh.rewardspoints.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private RewardsPointsService rewardsPointsService;

    @GetMapping("/total-previous-days/{days}")
    public int getPreviousDaysRewards(@PathVariable("days") int days, @RequestParam("customerId") Long customerId) {
        return rewardsPointsService.getTotalPointsByCustomerIdAndDays(customerId, days);
    }
}
