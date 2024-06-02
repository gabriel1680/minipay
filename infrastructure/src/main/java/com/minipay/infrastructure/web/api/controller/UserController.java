package com.minipay.infrastructure.web.api.controller;

import com.minipay.application.usecase.CreateUserUseCase;
import com.minipay.infrastructure.persistence.user.UserJpaRepository;
import com.minipay.infrastructure.web.api.dto.UserViewResponse;
import com.minipay.infrastructure.web.api.dto.CreateUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class UserController implements UserAPI {

    private final CreateUserUseCase createUserUseCase;

    private final UserJpaRepository repository;

    public UserController(CreateUserUseCase createUserUseCase, UserJpaRepository repository) {
        this.createUserUseCase = Objects.requireNonNull(createUserUseCase);
        this.repository = Objects.requireNonNull(repository);
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
        this.createUserUseCase.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<UserViewResponse>> getUsers() {
        return ResponseEntity.ok(repository.getAll());
    }
}
