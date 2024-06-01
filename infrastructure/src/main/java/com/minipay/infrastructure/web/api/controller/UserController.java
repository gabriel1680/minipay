package com.minipay.infrastructure.web.api.controller;

import com.minipay.application.CreateUserUseCase;
import com.minipay.infrastructure.web.api.dto.CreateUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserAPI {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @Override
    public ResponseEntity<?> createUser(final CreateUserRequest body) {
        final var input = new CreateUserUseCase.Input(
                body.name(),
                body.type(),
                body.email(),
                body.password(),
                body.documentType(),
                body.documentValue()
        );
        try {
            this.createUserUseCase.execute(input);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
