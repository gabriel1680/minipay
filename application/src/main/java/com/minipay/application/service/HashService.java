package com.minipay.application.service;

@FunctionalInterface
public interface HashService {
    String hash(String password);
}
