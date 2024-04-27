package com.somu.scheduler;


import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.somu.model.Customer;
import com.somu.repo.CustomerRepo;
import com.somu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class CustomerScheduler {

    @Autowired
    @Qualifier("jdbc")
    private CustomerService customerRepo;

//    @Scheduled(initialDelay = 10, fixedDelay = 1000)
    public void startScheduled(){
        System.out.println("Start Scheduled Customer...");
        Faker faker = new Faker();
        Name name = faker.name();
        String firstName = name.firstName();
        String lastName = name.lastName();
        customerRepo.saveAll(List.of(new Customer(firstName+" "+lastName, firstName.toLowerCase()+"."+lastName.toLowerCase()+"@code.buster", new Random().nextInt(10, 99))));
        System.out.println("End Scheduled Customer Inserted...");
    }
}
