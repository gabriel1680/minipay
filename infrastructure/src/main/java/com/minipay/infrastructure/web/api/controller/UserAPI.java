package com.minipay.infrastructure.web.api.controller;

import com.minipay.infrastructure.web.api.dto.CreateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "users")
public interface UserAPI {
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "422", description = "Domain validation fails"),
            @ApiResponse(responseCode = "400", description = "Malformed json body params"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )
    ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequest body);
}
