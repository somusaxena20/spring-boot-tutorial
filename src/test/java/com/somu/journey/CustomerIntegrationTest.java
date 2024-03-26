package com.somu.journey;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.somu.model.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    private static final String CUSTOMER_BASE_URL = "/api/v1/customer";

    @Test
    void canRegisterACustomer() {
        // create registration request
        // send a post request
        // get all customers
        // make sure that customer is present
        // get customer by id

        Faker faker = new Faker();
        Name name = faker.name();
        String fullName = name.fullName();
        String email = name.firstName().toLowerCase()+"."+name.lastName().toLowerCase()+ UUID.randomUUID()+"@foobar.com";
        Customer customer = new Customer(
                fullName,
                email,
                new Random().nextInt(1,100)
        );

        webTestClient.post()
                .uri(CUSTOMER_BASE_URL+"/provision")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(customer), Customer.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    // get all customers
    @Test
    void getAllCustomer() {

        Faker faker = new Faker();
        Name name = faker.name();
        String fullName = name.fullName();
        String email = name.firstName().toLowerCase()+"."+name.lastName().toLowerCase()+ UUID.randomUUID()+"@foobar.com";
        Customer customer = new Customer(
                fullName,
                email,
                new Random().nextInt(1,100)
        );

        List<Customer> responseBody = webTestClient.get()
                .uri(CUSTOMER_BASE_URL + "/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Customer>() {
                })
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(responseBody).usingRecursiveFieldByFieldElementComparatorIgnoringFields("id");
    }
}
