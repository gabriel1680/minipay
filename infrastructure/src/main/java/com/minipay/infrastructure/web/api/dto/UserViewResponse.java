package com.minipay.infrastructure.web.api.dto;

import java.math.BigDecimal;

public interface UserViewResponse {
    String getId();
    String getName();
    String getEmail();
    String getDocumentType();
    String getDocumentValue();
    BigDecimal getBalance();
}
