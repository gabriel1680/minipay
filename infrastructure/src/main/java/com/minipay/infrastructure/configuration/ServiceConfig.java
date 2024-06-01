package com.minipay.infrastructure.configuration;

import com.minipay.application.AuthorizationService;
import com.minipay.application.MailService;
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
}
