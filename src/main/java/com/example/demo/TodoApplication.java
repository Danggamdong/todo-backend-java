package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {

    public static void main(String... args) {

        SpringApplication sa = new SpringApplication(TodoApplication.class);
        sa.run();
    }
}
