package com.minipay.infrastructure.web.api.controller;

import com.minipay.infrastructure.web.api.dto.CreateTransferRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping(value = "transfers")
public interface TransferAPI {
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Creates a new transfer between two users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success"),
            @ApiResponse(responseCode = "422", description = "Error on domain constraints"),
            @ApiResponse(responseCode = "400", description = "Invalid Payload"),
            @ApiResponse(responseCode = "404", description = "Payee or Payer Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    ResponseEntity<?> performTransfer(@RequestBody @Valid CreateTransferRequest aRequest);
}
