package com.uma.security.api.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

import static com.uma.security.api.Constants.DXP;
import static com.uma.security.api.Constants.X_REQUEST_INITIATOR;

public class DXPApiFeignAuthConfig {

    @Bean
    public RequestInterceptor requestInterceptor(@Value("${uxp.api.auth.token}") String authToken) {
        return requestTemplate -> {
            requestTemplate.header(HttpHeaders.AUTHORIZATION, authToken);
            requestTemplate.header(X_REQUEST_INITIATOR, DXP);
        };
    }
}
