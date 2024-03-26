package com.somu.service.impl;

import com.github.javafaker.Faker;
import com.somu.AbstractTestcontainers;
import com.somu.mapper.CustomerRowMapper;
import com.somu.model.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


class CustomerJdbcImplTest extends AbstractTestcontainers {

    private CustomerJdbcImpl underTest;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();


    @BeforeEach
    void setUp() {
        underTest = new CustomerJdbcImpl(getJdbcTemplate(), customerRowMapper);
    }

    @Test
    void getAllCustomer() {
        //insert so data is available for this test
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().safeEmailAddress()+ UUID.randomUUID(),
                20
        );

        underTest.createCustomer(customer);

        List<Customer> allCustomer = underTest.getAllCustomer();
        Assertions.assertThat(allCustomer).isNotEmpty();
    }

    @Test
    void getCustomerById() {
        String email = FAKER.internet().safeEmailAddress() + UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                20
        );
        underTest.createCustomer(customer);

        Long id = underTest.getAllCustomer()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        Optional<Customer> actual = underTest.getCustomerById(Math.toIntExact(id));

        Assertions.assertThat(actual).isPresent().hasValueSatisfying(c ->{
            Assertions.assertThat(c.getId()).isEqualTo(id);
            Assertions.assertThat(c.getName()).isEqualTo(customer.getName());
            Assertions.assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            Assertions.assertThat(c.getAge()).isEqualTo(customer.getAge());
        });

    }

    @Test
    void willReturnEmptyWhenSelectCustomerById(){
        int id = -1;

        Optional<Customer> actual = underTest.getCustomerById(id);

        Assertions.assertThat(actual).isEmpty();
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
        String email = FAKER.internet().safeEmailAddress()+"-"+UUID.randomUUID();

        boolean actual = underTest.existsCustomerWithEmail(email);

        Assertions.assertThat(actual).isFalse();
    }

    protected static final Faker FAKER = new Faker();
}