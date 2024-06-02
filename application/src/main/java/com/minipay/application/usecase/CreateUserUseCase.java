package com.minipay.application.usecase;

import com.minipay.application.exception.EmailAlreadyTakenException;
import com.minipay.domain.user.User;
import com.minipay.domain.user.UserRepository;

import java.util.Objects;

public class CreateUserUseCase {
    final private UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
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
                anInput.password(),
                anInput.documentType(),
                anInput.documentValue()
        );
    }

    public record Input(String name, String type, String email, String password, String documentType, String documentValue) {
    }
}
