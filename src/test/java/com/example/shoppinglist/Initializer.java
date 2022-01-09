package com.example.shoppinglist;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Container
    static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:10")
            .withDatabaseName("local_db")
            .withUsername("admin")
            .withPassword("admin");

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        postgres.start();
        TestPropertyValues.of(
                "spring.datasource.url=" + postgres.getJdbcUrl(),
                "spring.datasource.username=" + postgres.getUsername(),
                "spring.datasource.password=" + postgres.getPassword()
        ).applyTo(applicationContext.getEnvironment());
    }
}
