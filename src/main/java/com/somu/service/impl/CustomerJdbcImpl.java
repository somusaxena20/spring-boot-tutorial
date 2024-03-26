package com.somu.service.impl;

import com.somu.mapper.CustomerRowMapper;
import com.somu.model.Customer;
import com.somu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("jdbc")
public class CustomerJdbcImpl implements CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CustomerRowMapper rowMapper;

    public CustomerJdbcImpl(JdbcTemplate jdbcTemplate, CustomerRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<Customer> getAllCustomer() {
        String sql = "SELECT * FROM customer";
        try{
            return jdbcTemplate.query(sql, rowMapper);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<Customer> getCustomerById(Integer cId) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        try{
            return jdbcTemplate.query(sql, rowMapper, cId).stream().findFirst();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        String sql = "INSERT INTO customer(name, email, age) VALUES(?, ?, ?)";
        try{
            int update = jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), customer.getAge());
            System.out.println("Insert Status : "+update);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public boolean deleteCustomer(Integer id) {
        String query = "DELETE FROM customer where id = ?";
        try{
            int update = jdbcTemplate.update(query, id);
            System.out.println("Update : "+update);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customer) {
        Optional<Customer> cust = getCustomerById(id);
        Customer response = cust.isPresent() ? cust.get() : null;

        if(response != null) {
            if (customer.getName() != null && !customer.getName().equals(response.getName())) {
                response.setName(customer.getName());
                String query = "UPDATE customer SET name = ? WHERE id = ?";
                try {
                    int update = jdbcTemplate.update(query, response.getName(), response.getId());
                    System.out.println("UPDATE : " + update);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (customer.getAge() != null && !customer.getAge().equals(response.getAge())) {
                response.setAge(customer.getAge());
                String query = "UPDATE customer SET age = ? WHERE id = ?";
                try {
                    int update = jdbcTemplate.update(query, response.getAge(), response.getId());
                    System.out.println("UPDATE : " + update);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (customer.getEmail() != null && (customer.getEmail().equals(response.getEmail()) || !existsCustomerWithEmail(customer.getEmail())) && !customer.getEmail().equals(response.getEmail())) {
                response.setEmail(customer.getEmail());
                String query = "UPDATE customer SET email = ? WHERE id = ?";
                try {
                    int update = jdbcTemplate.update(query, response.getEmail(), response.getId());
                    System.out.println("UPDATE : " + update);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }

    @Override
    public void saveAll(List<Customer> customerList) {
        String sql = "INSERT INTO customer(name, email, age) VALUES(?, ?, ?)";
        try{
            for(Customer customer : customerList) {
                int update = jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), customer.getAge());
                System.out.println("Insert Status : " + update);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        String query = "SELECT * FROM customer WHERE email = ?";
        try{
            List<Customer> customers = jdbcTemplate.query(query, rowMapper, email);
            if(!customers.isEmpty())
            {
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
