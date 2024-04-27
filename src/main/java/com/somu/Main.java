package com.somu;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication //single annotation for below three
//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@OpenAPIDefinition(info = @Info(title = "Somu Api Integration", version = "1.0", description = ""))
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
