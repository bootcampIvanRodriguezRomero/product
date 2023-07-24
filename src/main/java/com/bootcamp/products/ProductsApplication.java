package com.bootcamp.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * Main class for the Products application.
 * Initializes and runs the Spring Boot application.
 */

@SpringBootApplication
public class ProductsApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(ProductsApplication.class, args);
  }
  
}
