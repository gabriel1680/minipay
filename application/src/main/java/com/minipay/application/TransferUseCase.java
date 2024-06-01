package com.minipay.application;

import com.minipay.domain.TransferService;
import com.minipay.domain.UserRepository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class TransferUseCase {
    private final UserRepository userRepository;

    public TransferUseCase(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public void execute(final Input input) {
        final var payee = userRepository.get(input.payeeId).orElseThrow();
        final var payer = userRepository.get(input.payerId).orElseThrow();
        TransferService.performDebitCredit(payer, payee, BigDecimal.valueOf(input.amount));
        userRepository.save(payee);
        userRepository.save(payer);
    }

    public record Input(double amount, UUID payeeId, UUID payerId) {}
}
