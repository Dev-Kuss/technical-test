package com.mili.technical.test;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Price Service API",
        version = "1.0",
        description = "API for price calculations in the technical test"
    )
)
public class PriceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PriceServiceApplication.class, args);
    }
}
