package com.minipay.infrastructure.configuration;

import com.minipay.application.CreateUserUseCase;
import com.minipay.application.TransferUseCase;
import com.minipay.domain.TransferRepository;
import com.minipay.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    private final UserRepository userRepository;
    private final TransferRepository transferRepository;

    public UseCaseConfig(UserRepository userRepository, TransferRepository transferRepository) {
        this.userRepository = userRepository;
        this.transferRepository = transferRepository;
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserUseCase(userRepository);
    }

    @Bean
    public TransferUseCase transferUseCase() {
        return new TransferUseCase(userRepository, transferRepository);
    }
}
