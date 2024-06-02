package com.minipay.domain;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class HashFacade {

    private final Argon2Parameters parameters;

    public HashFacade(Argon2Parameters parameters) {
        this.parameters = parameters;
    }

    public static HashFacade start() {
        byte[] salt = getBytes();
        final var parameters = new Argon2Parameters
                .Builder(Argon2Parameters.ARGON2_VERSION_13)
                .withParallelism(1)
                .withIterations(2)
                .withMemoryAsKB(66536)
                .withVersion(1)
                .withSalt(salt)
                .build();
        return new HashFacade(parameters);
    }

    private static byte[] getBytes() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public String hash(String password) {
        final var generator = new Argon2BytesGenerator();
        generator.init(parameters);
        byte[] hash = new byte[32];
        generator.generateBytes(password.getBytes(StandardCharsets.UTF_8), hash, 0, hash.length);
        return Base64.getEncoder().encodeToString(hash);
    }
}
