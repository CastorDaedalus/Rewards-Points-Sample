package com.van.josh.rewardspoints.service;

import com.van.josh.rewardspoints.model.Customer;
import com.van.josh.rewardspoints.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId).get();
    }
}
