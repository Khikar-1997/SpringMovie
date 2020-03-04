package com.example.demo;

import com.example.demo.database.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        Database.databaseInitializer();
        SpringApplication.run(DemoApplication.class, args);
    }
}
