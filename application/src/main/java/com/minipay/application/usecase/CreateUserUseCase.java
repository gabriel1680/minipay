package com.minipay.application.usecase;

import com.minipay.application.exception.EmailAlreadyTakenException;
import com.minipay.application.service.HashService;
import com.minipay.domain.user.User;
import com.minipay.domain.user.UserRepository;

import java.util.Objects;

public class CreateUserUseCase {
    final private UserRepository userRepository;
    final private HashService hashService;

    public CreateUserUseCase(UserRepository userRepository, HashService hashService) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.hashService = Objects.requireNonNull(hashService);
    }

    public void execute(Input input) {
        if (userRepository.exists(input.email))
            throw new EmailAlreadyTakenException(input.email);
        final var user = createUserFrom(input);
        userRepository.save(user);
    }

    private User createUserFrom(Input anInput) {
        return User.create(
                anInput.name(),
                anInput.type(),
                anInput.email(),
                hashService.hash(anInput.password()),
                anInput.documentType(),
                anInput.documentValue()
        );
    }

    public record Input(String name, String type, String email, String password, String documentType, String documentValue) {
    }
}
