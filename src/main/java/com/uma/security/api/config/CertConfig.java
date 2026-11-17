package com.uma.security.api.config;

import jakarta.annotation.PostConstruct;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Generated
@Configuration
@Slf4j
public class CertConfig {

    @Value("${server.ssl.trust-store:classpath:keystores/trustStore.jks}")
    private Resource trustStoreFile;


    @PostConstruct
    void configureSSL() throws IOException {
        log.info("configureSSL() **********");

        File tempTrustStoreFile = File.createTempFile("tempTrustStore", ".jks");
        Files.copy(trustStoreFile.getInputStream(), tempTrustStoreFile.toPath(), REPLACE_EXISTING);
        log.info("****************** absolute Path: " + tempTrustStoreFile.getPath());
        System.setProperty("javax.net.ssl.trustStore", tempTrustStoreFile.getPath());
    }
}
