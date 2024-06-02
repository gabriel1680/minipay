package com.minipay.infrastructure.web.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UUID;

public record CreateTransferRequest(
        @NotEmpty @UUID @JsonProperty("payerId") String payerId,
        @NotEmpty @UUID @JsonProperty("payeeId")  String payeeId,
        @JsonProperty("amount") double amount
) {
}
