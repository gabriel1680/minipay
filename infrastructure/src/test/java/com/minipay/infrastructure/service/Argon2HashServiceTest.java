package com.minipay.infrastructure.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Argon2HashServiceTest {

    Argon2HashService sut;

    @BeforeEach
    void setUp() {
        sut = new Argon2HashService();
    }

    @Test
    void hash() {
        final var rawPass = "123";
        final var hashedPass = sut.hash(rawPass);
        assertTrue(sut.verify(hashedPass, rawPass));
    }
}