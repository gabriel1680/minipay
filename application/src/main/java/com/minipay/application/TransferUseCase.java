package com.minipay.application;

import com.minipay.domain.*;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class TransferUseCase {
    private final UserRepository userRepository;
    private final TransferRepository transferRepository;
    private final EventDispatcher dispatcher;

    public TransferUseCase(UserRepository userRepository, TransferRepository transferRepository, EventDispatcher dispatcher) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.transferRepository = Objects.requireNonNull(transferRepository);
        this.dispatcher = Objects.requireNonNull(dispatcher);
    }

    @Transactional
    public void execute(final Input input) {
        final var payee = userRepository.get(UUID.fromString(input.payeeId())).orElseThrow();
        final var payer = userRepository.get(UUID.fromString(input.payerId())).orElseThrow();
        final var transfer = TransferService.performDebitCredit(payer, payee, BigDecimal.valueOf(input.amount));
        userRepository.save(payee);
        userRepository.save(payer);
        transferRepository.save(transfer);
        transfer.getEvents().forEach(dispatcher::publish);
    }

    public record Input(String payerId, String payeeId, double amount) {}
}
