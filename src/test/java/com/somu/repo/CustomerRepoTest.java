package com.somu.repo;

import com.somu.AbstractTestcontainers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepoTest extends AbstractTestcontainers {

    @Autowired
    private CustomerRepo underTest;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        System.out.println(applicationContext.getBeanDefinitionCount());
    }

    @Test
    void existsCustomerByEmail() {

    }

    @Test
    void existsCustomerById() {
    }
}