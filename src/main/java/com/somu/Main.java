package com.somu;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.somu.model.Customer;
import com.somu.repo.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Random;

@EnableScheduling
@SpringBootApplication //single annotation for below three
//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world...");
        SpringApplication.run(Main.class, args);
    }

//    @Bean
//    CommandLineRunner runner(CustomerRepo repo){
//        return args -> {
//            Faker faker = new Faker();
//            Name name = faker.name();
//            String firstName = name.firstName();
//            String lastName = name.lastName();
//            repo.saveAll(List.of(new Customer(firstName+" "+lastName, firstName.toLowerCase()+"."+lastName.toLowerCase()+"@code.buster", new Random().nextInt(10, 99))));
//        };
//    }

}
