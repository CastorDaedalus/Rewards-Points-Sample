package com.van.josh.rewardspoints.controller;

import com.van.josh.rewardspoints.model.Customer;
import com.van.josh.rewardspoints.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        ResponseEntity<Customer> response = ResponseEntity.ok(customerService.getCustomer(id));
        return response;
    }
}
