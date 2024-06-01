package com.minipay.infrastructure.service;

import com.minipay.application.service.AuthorizationService;

import java.math.BigDecimal;

public class AuthorizationMemoryService implements AuthorizationService {
    @Override
    public boolean authorize(BigDecimal amount) {
        return true;
    }
}
