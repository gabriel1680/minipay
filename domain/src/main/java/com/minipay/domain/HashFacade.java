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
        byte[] hash = createHash();
        generator.generateBytes(password.getBytes(StandardCharsets.UTF_8), hash, 0, hash.length);
        return getEncodeToString(hash);
    }

    public boolean verify(String hash, String raw) {
        Argon2BytesGenerator verifier = new Argon2BytesGenerator();
        verifier.init(parameters);
        byte[] testHash = createHash();
        verifier.generateBytes(raw.getBytes(StandardCharsets.UTF_8), testHash, 0, testHash.length);
        return getEncodeToString(testHash).equals(hash);
    }

    private byte[] createHash() {
        return new byte[32];
    }

    private String getEncodeToString(final byte[] hash) {
        return Base64.getEncoder().encodeToString(hash);
    }
}
