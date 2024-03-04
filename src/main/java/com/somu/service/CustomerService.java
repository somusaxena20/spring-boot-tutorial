package com.somu.service;

import com.somu.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomer();

    Optional<Customer> getCustomerById(Integer cId);
}
