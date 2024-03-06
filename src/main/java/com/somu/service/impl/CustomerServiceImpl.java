package com.somu.service.impl;

import com.somu.repo.CustomerRepo;
import com.somu.model.Customer;
import com.somu.service.CustomerService;
import com.somu.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Override
    public Customer createCustomer(Customer customer){
        if(!customerRepo.existsCustomerByEmail(customer.getEmail())) {
            customerRepo.save(customer);
        }
        else{
            return null;
        }
        return customer;
    }

    @Override
    public boolean deleteCustomer(Integer id) {
        if(customerRepo.existsCustomerById(id)){
            customerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customer) {
        if(customerRepo.existsCustomerById(id))
        {
            if(getCustomerById(id).isPresent())
            {
                Customer cust = getCustomerById(id).get();
                if(customer.getEmail() != null && !customer.getEmail().equalsIgnoreCase(cust.getEmail()))
                {
                    cust.setEmail(customer.getEmail());
                }
                if(customer.getAge() != null && customer.getAge() != cust.getAge())
                {
                    cust.setAge(customer.getAge());
                }
                if(customer.getName() != null && !customer.getName().equalsIgnoreCase(cust.getName()))
                {
                    cust.setName(customer.getName());
                }
                customerRepo.save(cust);
                return cust;
            }
            return null;
        }
        return null;
    }
}
