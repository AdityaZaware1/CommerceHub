package com.ben.apis.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${services.user.url:http://localhost:8089}")
    private String userServiceUrl;

    @Value("${services.store.url:http://localhost:8082}")
    private String storeServiceUrl;

    @Bean
    public GroupedOpenApi userServiceApi() {
        return GroupedOpenApi.builder()
                .group("User Service")
                .pathsToMatch("/api/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi storeServiceApi() {
        return GroupedOpenApi.builder()
                .group("Store Service")
                .pathsToMatch("/api/store/**")
                .build();
    }
}
