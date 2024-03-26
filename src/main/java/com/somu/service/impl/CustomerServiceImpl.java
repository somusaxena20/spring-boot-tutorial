package com.somu.service.impl;

import com.somu.repo.CustomerRepo;
import com.somu.model.Customer;
import com.somu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("jpa")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

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

    @Override
    public void saveAll(List<Customer> customerList) {
        try{
            customerRepo.saveAll(customerList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return customerRepo.existsCustomerByEmail(email);
    }
}
