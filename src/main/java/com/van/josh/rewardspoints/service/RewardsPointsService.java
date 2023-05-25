package com.van.josh.rewardspoints.service;

import com.van.josh.rewardspoints.model.RewardsPoints;
import com.van.josh.rewardspoints.model.RewardsPointsConfig;
import com.van.josh.rewardspoints.model.Transaction;
import com.van.josh.rewardspoints.repository.RewardsPointsConfigRepository;
import com.van.josh.rewardspoints.repository.RewardsPointsRepository;
import com.van.josh.rewardspoints.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class RewardsPointsService {

    @Autowired
    RewardsPointsConfigRepository rewardsPointsConfigRepository;

    @Autowired
    RewardsPointsRepository rewardsPointsRepository;

    @Autowired
    TransactionRepository transactionRepository;

    RewardsPointsConfig  rewardsPointsConfig;

    private void getConfiguration(){
        Iterator<RewardsPointsConfig> iterator = rewardsPointsConfigRepository.findAll().iterator();
        if(iterator.hasNext()) {
            this.rewardsPointsConfig = iterator.next();
        } else{
            //TODO: throw exception
        }
    }

    public void createRewardsEntry(Transaction transaction) {
        getConfiguration();

        BigDecimal points = calculateRewardsPoints(transaction);

        RewardsPoints rewards = new RewardsPoints();
        rewards.setPoints(points);
        rewards.setCustomerId(transaction.getCustomerId());
        rewards.setTransactionId(transaction.getId());

        rewardsPointsRepository.save(rewards);

    }

    public void createRewardsEntries(List<Transaction> transactions) {
        getConfiguration();

        List<RewardsPoints> rewardsList = new ArrayList<>();

        transactions.forEach(transaction -> {
            BigDecimal points = calculateRewardsPoints(transaction);

            RewardsPoints rewards = new RewardsPoints();
            rewards.setPoints(points);
            rewards.setCustomerId(transaction.getCustomerId());
            rewards.setTransactionId(transaction.getId());

            rewardsList.add(rewards);
        });

        rewardsPointsRepository.saveAll(rewardsList);

    }

    private BigDecimal calculateRewardsPoints(Transaction transaction){
        if(transaction.getAmount().compareTo(rewardsPointsConfig.getPointsPerDollarThreshold()) >= 0
        && transaction.getAmount().compareTo(rewardsPointsConfig.getPointsPerDollarMultiplierThreshold()) <= 0){
            //Value is above 50  and below 100
            //Subtract first 50 points
            BigDecimal points = transaction.getAmount().subtract(rewardsPointsConfig.getPointsPerDollarThreshold());
            //Multiply by points multiplier for values over 50 (1 in thise case)
            //Round up for the customer
            points = points.multiply(rewardsPointsConfig.getPointsPerDollar().setScale(0, RoundingMode.CEILING));

            return points;

        } else if(transaction.getAmount().compareTo(rewardsPointsConfig.getPointsPerDollarMultiplierThreshold()) >= 0){
            //Subtract first 100 points
            BigDecimal points = transaction.getAmount().subtract(rewardsPointsConfig.getPointsPerDollarMultiplierThreshold());
            //Multiply by points multiplier for values over 100
            points = points.multiply(rewardsPointsConfig.getPointsPerDollarMultiplier());
            //Add points for initial threshold of 50 multiplied by standard rewards value (1 in this case)
            //Round up for the customer
            points = points.add(rewardsPointsConfig.getPointsPerDollar().multiply(rewardsPointsConfig.getPointsPerDollarThreshold())).setScale(0, RoundingMode.CEILING);

            return points;
        }
        return BigDecimal.ZERO;
    }


    public int getTotalPointsByCustomerIdAndDays(Long customerId, int days) {
        if(days >= 0){
            LocalDate currentDate = LocalDate.now();
            LocalDate pastDate = currentDate.minusDays(days);
            List<RewardsPoints> rewardsPointsList = rewardsPointsRepository.findByCustomerIdAndTransactionDateBetween(customerId, pastDate, currentDate);
            return rewardsPointsList.stream().mapToInt(rewardsPoints -> rewardsPoints.getPoints().intValue()).sum();
        }
        return 0;
    }
}
