package com.example.shoppinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ShoppingListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingListApplication.class, args);
    }

}
