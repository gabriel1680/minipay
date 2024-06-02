package com.minipay.infrastructure.web.api.dto;

import java.math.BigDecimal;

public interface TransferViewResponse {
    String getId();
    String getPayeeName();
    String getPayeeEmail();
    BigDecimal getAmount();
}
