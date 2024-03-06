package com.somu;

import com.somu.model.Customer;
import com.somu.repo.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
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
//            repo.saveAll(List.of(new Customer("Alex", "alex@gmail.com", 20), new Customer("Godless", "godless@gmail.com", 19)));
//        };
//    }

}
