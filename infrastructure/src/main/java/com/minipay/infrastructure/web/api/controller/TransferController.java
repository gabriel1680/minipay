package com.minipay.infrastructure.web.api.controller;

import com.minipay.application.TransferUseCase;
import com.minipay.infrastructure.web.api.dto.CreateTransferRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController implements TransferAPI {

    private final TransferUseCase transferUseCase;

    public TransferController(TransferUseCase transferUseCase) {
        this.transferUseCase = transferUseCase;
    }

    @Override
    public ResponseEntity<?> performTransfer(CreateTransferRequest aRequest) {
        final var input = new TransferUseCase.Input(
                aRequest.payerId(),
                aRequest.payeeId(),
                aRequest.amount()
        );
        try {
            transferUseCase.execute(input);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
