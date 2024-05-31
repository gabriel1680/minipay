package com.minipay.infrastructure.web.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateUserRequest(
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("document") String document,
        @JsonProperty("documentType") String documentType
) {
}
