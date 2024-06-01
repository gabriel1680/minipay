package com.minipay.application.service;

import java.math.BigDecimal;

public interface AuthorizationService {
    boolean authorize(BigDecimal amount);
}
