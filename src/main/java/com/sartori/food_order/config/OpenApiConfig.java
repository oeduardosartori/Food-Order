package com.sartori.food_order.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Food Order API")
                        .version("v1.0")
                        .description("API for food order management")
                        .contact(new Contact()
                                .name("Eduardo Sartori")
                                .email("oeduardosartori@icloud.com")
                                .url("https://github.com/eduardosartori")));
    }
}
