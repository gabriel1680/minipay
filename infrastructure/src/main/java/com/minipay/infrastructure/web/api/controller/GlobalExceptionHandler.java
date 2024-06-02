package com.minipay.infrastructure.web.api.controller;

import com.minipay.application.exception.ApplicationException;
import com.minipay.application.exception.NotFoundException;
import com.minipay.domain.DomainException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> domainException(final DomainException e) {
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<?> applicationException(final ApplicationException e) {
        return e instanceof NotFoundException ?
                ResponseEntity.notFound().build() :
                ResponseEntity.unprocessableEntity().body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> anyException(final RuntimeException e) {
        return ResponseEntity.internalServerError().build();
    }
}
