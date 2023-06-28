package com.example.specifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpecificationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpecificationsApplication.class, args);
    }
}
