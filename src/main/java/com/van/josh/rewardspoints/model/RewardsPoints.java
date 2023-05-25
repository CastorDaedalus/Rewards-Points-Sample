package com.van.josh.rewardspoints.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Table("REWARDS_POINTS")
public class RewardsPoints {

    @Id
    @Column(value = "ID")
    private Long id;

    private BigDecimal points;

    private Long transactionId;

    private Long customerId;

    public RewardsPoints() {

    }

    public RewardsPoints(BigDecimal points) {
        this.points = points;
    }
}
