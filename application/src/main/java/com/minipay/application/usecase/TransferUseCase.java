package com.minipay.application.usecase;

import com.minipay.application.exception.NotFoundException;
import com.minipay.domain.event.DomainEventDispatcher;
import com.minipay.domain.transfer.TransferRepository;
import com.minipay.domain.TransferService;
import com.minipay.domain.user.UserRepository;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class TransferUseCase {
    private final UserRepository userRepository;
    private final TransferRepository transferRepository;
    private final DomainEventDispatcher dispatcher;

    public TransferUseCase(UserRepository userRepository, TransferRepository transferRepository, DomainEventDispatcher dispatcher) {
        this.userRepository = Objects.requireNonNull(userRepository);
        this.transferRepository = Objects.requireNonNull(transferRepository);
        this.dispatcher = Objects.requireNonNull(dispatcher);
    }

    @Transactional
    public void execute(final Input input) {
        final var payee = userRepository.get(UUID.fromString(input.payeeId()))
                .orElseThrow(() -> new NotFoundException("Payee not found"));
        final var payer = userRepository.get(UUID.fromString(input.payerId()))
                .orElseThrow(() -> new NotFoundException("Payer not found"));
        final var transfer = TransferService.performDebitCredit(payer, payee, BigDecimal.valueOf(input.amount));
        userRepository.save(payee);
        userRepository.save(payer);
        transferRepository.save(transfer);
        transfer.publishEvents(dispatcher);
    }

    public record Input(String payerId, String payeeId, double amount) {}
}
