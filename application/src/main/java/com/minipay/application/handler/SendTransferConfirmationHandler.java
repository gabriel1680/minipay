package com.minipay.application.handler;

import com.minipay.application.service.MailService;
import com.minipay.domain.event.EventHandler;
import com.minipay.domain.transfer.TransferCreated;
import com.minipay.domain.user.User;
import com.minipay.domain.user.UserRepository;

import java.util.Objects;

public class SendTransferConfirmationHandler implements EventHandler<TransferCreated> {
    private final MailService mailService;
    private final UserRepository userRepository;

    public SendTransferConfirmationHandler(MailService mailService, UserRepository userRepository) {
        this.mailService = Objects.requireNonNull(mailService);
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public boolean canHandle(Class<?> event) {
        return event.equals(TransferCreated.class);
    }

    @Override
    public void handle(TransferCreated event) {
        final var payer = userRepository.get(event.payerId()).map(User::getName).orElseThrow();
        final var payee = userRepository.get(event.payeeId()).map(User::getName).orElseThrow();
        final var subject = "Your transfer have been finished!";
        final var body = """
                Hi %s.
                Your transfer of %s have been sent from %s.
                Cheers!
                """.formatted(payee, event.amount(), payer);
        mailService.send(payee, subject, body).orElseRun(e -> System.out.println(e.getMessage()));
    }
}
