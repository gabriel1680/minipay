package com.minipay.application;

import com.minipay.domain.User;
import com.minipay.domain.UserFactory;
import com.minipay.domain.UserRepository;

import java.util.Objects;
import java.util.UUID;

public class CreateUserUseCase {
    final private UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public void execute(Input input) {
        if (userRepository.exists(input.email))
            throw new RuntimeException("Email already taken");
        final var user = createUserFrom(input);
        userRepository.save(user);
    }

    private User createUserFrom(Input anInput) {
        return UserFactory.create(
                UUID.randomUUID(),
                anInput.name(),
                anInput.email(),
                anInput.password(),
                anInput.documentType(),
                anInput.documentValue()
        );
    }

    public record Input(String name, String email, String password, String documentType, String documentValue) {
    }
}
