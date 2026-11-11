package com.example.libraryjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan // finds LibraryProperties
public class LibraryJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryJpaApplication.class, args);
    }
}
