package com.minipay.infrastructure.configuration;

import com.minipay.application.*;
import com.minipay.domain.EventDispatcher;
import com.minipay.domain.TransferRepository;
import com.minipay.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class UseCaseConfig {
    private final UserRepository userRepository;
    private final TransferRepository transferRepository;
    private final EventDispatcher dispatcher;
    private final AuthorizationService authorizationService;
    private final MailService mailService;

    public UseCaseConfig(UserRepository userRepository, TransferRepository transferRepository, AuthorizationService authorizationService, MailService mailService) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.transferRepository = Objects.requireNonNull(transferRepository);
        this.authorizationService = Objects.requireNonNull(authorizationService);
        this.mailService = Objects.requireNonNull(mailService);
        this.dispatcher = EventDispatcher.getInstance();
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserUseCase(userRepository);
    }

    @Bean
    public TransferUseCase transferUseCase() {
        dispatcher.register(new AuthorizeTransferHandler(authorizationService));
        dispatcher.register(new SendTransferConfirmationHandler(mailService, userRepository));
        return new TransferUseCase(userRepository, transferRepository, dispatcher);
    }
}
