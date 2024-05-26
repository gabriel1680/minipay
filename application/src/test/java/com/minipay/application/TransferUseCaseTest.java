package com.minipay.application;

import com.minipay.application.fixture.MemoryUserRepository;
import com.minipay.domain.UserType;
import com.minipay.domain.fixture.UserBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransferUseCaseTest {

    MemoryUserRepository userRepository;
    TransferUseCase sut;

    @BeforeEach
    void setup() {
        userRepository = new MemoryUserRepository();
        sut = new TransferUseCase(userRepository);
    }

    @Test
    void execute() {
        final var payee = UserBuilder.aUser().withId(1).withBalance(10).build();
        userRepository.save(payee);
        final var payer = UserBuilder.aUser().withId(2).withBalance(10).ofType(UserType.COMMON).build();
        userRepository.save(payer);
        final var input = new TransferUseCase.Input(10.0, payee.getId(), payer.getId());
        sut.execute(input);
        assertUserBalance(payee.getId(), "20.0");
        assertUserBalance(payer.getId(), "0.0");
    }

    void assertUserBalance(final Integer userId, final String balance) {
        Assertions.assertEquals(
                balance,
                userRepository
                .get(userId)
                .map(u -> u.getWallet()
                        .balance()
                        .toString())
                .get());
    }
}