package com.minipay.application;

import io.vavr.control.Either;

public interface MailService {
    Either<RuntimeException, Integer> send(String to, String subject, String body);
}
