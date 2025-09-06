package com.product.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class SwaggerCofig {

    @Bean
    public OpenAPI productOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product API")
                        .description("API documentation for Product Service")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .servers(List.of(new Server().url("http://localhost:9021").description("Local Server")));
    }
}



