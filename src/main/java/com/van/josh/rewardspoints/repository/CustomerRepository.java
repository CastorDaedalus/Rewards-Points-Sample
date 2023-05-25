package com.van.josh.rewardspoints.repository;

import com.van.josh.rewardspoints.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
