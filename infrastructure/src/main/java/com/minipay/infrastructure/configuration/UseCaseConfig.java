package com.minipay.infrastructure.configuration;

import com.minipay.application.CreateUserUseCase;
import com.minipay.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    private final UserRepository userGateway;

    public UseCaseConfig(UserRepository userGateway) {
        this.userGateway = userGateway;
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserUseCase(userGateway);
    }
}
