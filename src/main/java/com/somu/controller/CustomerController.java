package com.somu.controller;

import com.somu.model.Customer;
import com.somu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
    public List<Customer> getCustomer()
    {
        return customerService.getAllCustomer();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable("id") Integer id){
        return customerService.getCustomerById(id).isPresent() ? customerService.getCustomerById(id).get() : new Customer();
    }
}
