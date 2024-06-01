package com.minipay.infrastructure.service;

import com.minipay.application.service.MailService;
import io.vavr.control.Either;

public class MailMemoryService implements MailService {
    @Override
    public Either<RuntimeException, Integer> send(String to, String subject, String body) {
        System.out.println(body);
        return Either.right(0);
    }
}
