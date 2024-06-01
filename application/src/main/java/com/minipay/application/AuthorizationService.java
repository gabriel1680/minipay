package com.minipay.application;

import java.math.BigDecimal;

public interface AuthorizationService {
    boolean authorize(BigDecimal amount);
}
