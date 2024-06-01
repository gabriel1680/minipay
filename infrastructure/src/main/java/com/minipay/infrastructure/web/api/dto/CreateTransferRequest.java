package com.minipay.infrastructure.web.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateTransferRequest(
        @JsonProperty("payerId") String payerId,
        @JsonProperty("payeeId")  String payeeId,
        @JsonProperty("amount") double amount
) {
}
