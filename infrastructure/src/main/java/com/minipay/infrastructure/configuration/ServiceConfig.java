package com.minipay.infrastructure.configuration;

import com.minipay.application.service.AuthorizationService;
import com.minipay.application.service.HashService;
import com.minipay.application.service.MailService;
import com.minipay.infrastructure.service.Argon2HashService;
import com.minipay.infrastructure.service.AuthorizationMemoryService;
import com.minipay.infrastructure.service.MailMemoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ServiceConfig {

    @Profile({ "dev" })
    @Bean
    public AuthorizationService getAuthorizationService() {
        return new AuthorizationMemoryService();
    }

    @Profile({ "dev" })
    @Bean
    public MailService getMailService() {
        return new MailMemoryService();
    }

    @Bean
    public HashService getHashService() {
        return new Argon2HashService();
    }
}
