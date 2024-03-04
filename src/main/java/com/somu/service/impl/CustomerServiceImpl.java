package com.somu.service.impl;

import com.somu.repo.CustomerRepo;
import com.somu.model.Customer;
import com.somu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepo.findById(id);
    }
}
