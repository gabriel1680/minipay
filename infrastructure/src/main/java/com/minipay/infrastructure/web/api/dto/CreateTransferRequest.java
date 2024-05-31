package com.minipay.infrastructure.web.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record CreateTransferRequest(
        @JsonProperty("payerId") String payerId,
        @JsonProperty("payeeId")  String payee,
        @JsonProperty("amount") BigDecimal amount
) {
}
