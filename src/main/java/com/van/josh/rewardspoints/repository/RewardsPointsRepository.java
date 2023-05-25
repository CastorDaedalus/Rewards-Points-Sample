package com.van.josh.rewardspoints.repository;

import com.van.josh.rewardspoints.model.RewardsPoints;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RewardsPointsRepository extends CrudRepository<RewardsPoints, Long> {

    List<RewardsPoints> findByCustomerId(Long customerId);

    RewardsPoints findByTransactionId(Long transactionId);

    @Query("SELECT r.* FROM rewards_points r JOIN TRANSACTION t ON r.transaction_id = t.id WHERE t.customer_id = :customerId AND t.transaction_date >= :startDate AND t.transaction_date <= :endDate")
    List<RewardsPoints> findByCustomerIdAndTransactionDateBetween(@Param("customerId") Long customerId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
