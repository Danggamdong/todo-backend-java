package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class TodoApplication {

    public static void main(String... args) {
        Properties defaultProperties = new Properties();
        defaultProperties.put("server.port", "12345");
        defaultProperties.put("server.address", "0.0.0.0");

        SpringApplication sa = new SpringApplication(TodoApplication.class);
        sa.setDefaultProperties(defaultProperties);
        sa.run();
    }
}
