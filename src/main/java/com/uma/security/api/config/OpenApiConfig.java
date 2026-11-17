package com.uma.security.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
public class OpenApiConfig {

    @Value("${springdoc.server.url:}")
    private String serverUrl;

    @Value("${springdoc.api.version: 1.0}")
    private String apiVersion;

    @Value("${springdoc.api.title:}")
    private String apiTitle;

    @Value("${springdoc.api.description:}")
    private String apiDescription;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(createComponents())
                .info(createInfo())
                .addServersItem(createServer(serverUrl))
                .addSecurityItem(createSecurityItem());
    }

    private Components createComponents() {
        return new Components()
                .addSecuritySchemes(AUTHORIZATION, createSecurityScheme());
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name(AUTHORIZATION)
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER);
    }

    private Info createInfo() {
        return new Info()
                .title(apiTitle)
                .version(apiVersion)
                .description(apiDescription);
    }

    Server createServer(String serverUrl) {
        return new Server().url(serverUrl);
    }
    private SecurityRequirement createSecurityItem() {
        return new SecurityRequirement().addList(AUTHORIZATION);
    }
}
