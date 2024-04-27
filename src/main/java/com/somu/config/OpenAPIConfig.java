package com.somu.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

  @Bean
  public OpenAPI myOpenAPI() {

    Contact contact = new Contact();
    contact.setEmail("somusaxena20@gmail.com");
    contact.setName("SOMU SAXENA");
    contact.setUrl("https://hub.docker.com/u/somusaxena20");

    License mitLicense = new License().name("MIT License").url("https://hub.docker.com/u/somusaxena20");

    Info info = new Info()
        .title("Tutorial Management API")
        .version("1.0")
        .contact(contact)
        .description("This API exposes endpoints to manage tutorials.").termsOfService("https://hub.docker.com/u/somusaxena20")
        .license(mitLicense);

    return new OpenAPI().info(info);
  }
}
