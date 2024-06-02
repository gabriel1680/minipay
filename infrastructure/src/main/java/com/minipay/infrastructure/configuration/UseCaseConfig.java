package com.minipay.infrastructure.configuration;

import com.minipay.application.handler.AuthorizeTransferHandler;
import com.minipay.application.handler.SendTransferConfirmationHandler;
import com.minipay.application.service.AuthorizationService;
import com.minipay.application.service.HashService;
import com.minipay.application.service.MailService;
import com.minipay.application.usecase.CreateUserUseCase;
import com.minipay.application.usecase.TransferUseCase;
import com.minipay.domain.event.MemoryEventDispatcher;
import com.minipay.domain.transfer.TransferRepository;
import com.minipay.domain.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class UseCaseConfig {
    private final UserRepository userRepository;
    private final TransferRepository transferRepository;
    private final MemoryEventDispatcher dispatcher;
    private final AuthorizationService authorizationService;
    private final MailService mailService;
    private final HashService hashService;

    public UseCaseConfig(UserRepository userRepository, TransferRepository transferRepository, AuthorizationService authorizationService, MailService mailService, HashService hashService) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.transferRepository = Objects.requireNonNull(transferRepository);
        this.authorizationService = Objects.requireNonNull(authorizationService);
        this.mailService = Objects.requireNonNull(mailService);
        this.hashService = Objects.requireNonNull(hashService);
        this.dispatcher = MemoryEventDispatcher.getInstance();
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserUseCase(userRepository, hashService);
    }

    @Bean
    public TransferUseCase transferUseCase() {
        dispatcher.register(new AuthorizeTransferHandler(authorizationService));
        dispatcher.register(new SendTransferConfirmationHandler(mailService, userRepository));
        return new TransferUseCase(userRepository, transferRepository, dispatcher);
    }
}
