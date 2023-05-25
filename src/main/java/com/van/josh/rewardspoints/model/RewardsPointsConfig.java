package com.van.josh.rewardspoints.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Table("REWARDS_POINTS_CONFIG")
public class RewardsPointsConfig {

    @Id
    @Column(value = "ID")
    private Long id;

    private BigDecimal pointsPerDollar;

    private BigDecimal pointsPerDollarThreshold;

    private BigDecimal pointsPerDollarMultiplier;

    private BigDecimal pointsPerDollarMultiplierThreshold;

    public RewardsPointsConfig() {
    }

    public RewardsPointsConfig(BigDecimal pointsPerDollar, BigDecimal pointsPerDollarThreshold, BigDecimal pointsPerDollarMultiplier, BigDecimal pointsPerDollarMultiplierThreshold) {
        this.pointsPerDollar = pointsPerDollar;
        this.pointsPerDollarThreshold = pointsPerDollarThreshold;
        this.pointsPerDollarMultiplier = pointsPerDollarMultiplier;
        this.pointsPerDollarMultiplierThreshold = pointsPerDollarMultiplierThreshold;
    }

}
