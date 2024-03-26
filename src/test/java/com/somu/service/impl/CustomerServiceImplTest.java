package com.somu.service.impl;

import com.somu.repo.CustomerRepo;
import com.somu.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class CustomerServiceImplTest {
    private CustomerServiceImpl underTest;
    @Mock
    private CustomerRepo customerRepo;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerServiceImpl(customerRepo);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllCustomer() {
        //when
        underTest.getAllCustomer();

        //then
        verify(customerRepo).findAll();

    }

    @Test
    void getCustomerById() {
        int id = -1;

        underTest.getCustomerById(id);

        verify(customerRepo).findById(id);
    }

    @Test
    void createCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void saveAll() {
    }

    @Test
    void existsCustomerWithEmail() {
    }
}