package com.minipay.infrastructure.web.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CreateUserRequest(
        @NotEmpty @JsonProperty("name") String name,
        @NotEmpty @Email @JsonProperty("email") String email,
        @NotEmpty @JsonProperty("type") String type,
        @NotEmpty @JsonProperty("password") String password,
        @NotEmpty @JsonProperty("documentValue") String documentValue,
        @NotEmpty @JsonProperty("documentType") String documentType
) {
}
