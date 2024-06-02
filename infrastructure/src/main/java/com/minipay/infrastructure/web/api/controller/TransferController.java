package com.minipay.infrastructure.web.api.controller;

import com.minipay.application.usecase.TransferUseCase;
import com.minipay.infrastructure.persistence.transfer.TransferJpaRepository;
import com.minipay.infrastructure.web.api.dto.TransferViewResponse;
import com.minipay.infrastructure.web.api.dto.CreateTransferRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class TransferController implements TransferAPI {

    private final TransferUseCase transferUseCase;

    private final TransferJpaRepository repository;

    public TransferController(TransferUseCase transferUseCase, TransferJpaRepository repository) {
        this.transferUseCase = Objects.requireNonNull(transferUseCase);
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public ResponseEntity<?> performTransfer(CreateTransferRequest aRequest) {
        final var input = new TransferUseCase.Input(
                aRequest.payerId(),
                aRequest.payeeId(),
                aRequest.amount()
        );
        transferUseCase.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<TransferViewResponse>> getTransactionsOf(String userId) {
        return ResponseEntity.ok(repository.getAllTransfersOf(userId));
    }
}
