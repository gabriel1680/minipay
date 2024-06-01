package com.minipay.application;

import com.minipay.application.fixture.MemoryTransferRepository;
import com.minipay.application.fixture.MemoryUserRepository;
import com.minipay.domain.EventDispatcher;
import com.minipay.domain.fixture.UserBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class TransferUseCaseTest {

    EventDispatcher dispatcher;
    MemoryUserRepository userRepository;
    MemoryTransferRepository transferRepository;
    TransferUseCase sut;

    @BeforeEach
    void setup() {
        dispatcher = EventDispatcher.fresh();
        userRepository = new MemoryUserRepository();
        transferRepository = new MemoryTransferRepository();
        sut = new TransferUseCase(userRepository, transferRepository, dispatcher);
    }

    @Test
    void execute() {
        final var payee = UserBuilder.aUser()
                .withId("9eaca85e-6eac-43ca-bdde-22df31b5c248")
                .withBalance(10)
                .build();
        userRepository.save(payee);
        final var payer = UserBuilder.aUser()
                .withId("10a3c615-d989-406c-b586-8f9c2341d843")
                .withBalance(10)
                .build();
        userRepository.save(payer);
        final var input = new TransferUseCase.Input(payer.getId().toString(), payee.getId().toString(), 10.0);
        sut.execute(input);
        assertUserBalance(payee.getId(), "20.0");
        assertUserBalance(payer.getId(), "0.0");
        assertTransferCount(1);
    }

    private void assertTransferCount(int i) {
        Assertions.assertEquals(i, transferRepository.count());
    }

    private void assertUserBalance(final UUID userId, final String balance) {
        Assertions.assertEquals(
                balance,
                userRepository
                .get(userId)
                .map(u -> u.getBalance().toString())
                .get());
    }
}