package com.minipay.infrastructure.web.api.controller;

import com.minipay.infrastructure.web.api.dto.CreateTransferRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController implements TransferAPI {
    @Override
    public ResponseEntity<?> performTransfer(CreateTransferRequest aRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
