package com.minipay.infrastructure.web.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateUserRequest(
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("documentValue") String documentValue,
        @JsonProperty("documentType") String documentType
) {
}
