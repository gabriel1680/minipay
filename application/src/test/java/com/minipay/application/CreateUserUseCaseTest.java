package com.minipay.application;

import com.minipay.application.fixture.CreateUserInputBuilder;
import com.minipay.application.fixture.MemoryUserRepository;
import com.minipay.application.usecase.CreateUserUseCase;
import com.minipay.domain.fixture.UserBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateUserUseCaseTest {

    MemoryUserRepository userRepository;
    CreateUserUseCase sut;

    @BeforeEach
    void setUp() {
        userRepository = new MemoryUserRepository();
        sut = new CreateUserUseCase(userRepository);
    }

    @Test
    void executeWhenEmailExists() {
        final var email = "some@gmail.com";
        final var existentUser = UserBuilder.aUser().withCredentials(email, "123").build();
        userRepository.save(existentUser);
        final var input = CreateUserInputBuilder.anInput().withEmail(email).build();
        Assertions.assertThrows(RuntimeException.class, () -> sut.execute(input));
    }

    @Test
    void execute() {
        final var email = "some@gmail.com";
        final var input = CreateUserInputBuilder.anInput().withEmail(email).build();
        sut.execute(input);
        Assertions.assertTrue(userRepository.exists(email));
    }
}