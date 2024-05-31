package com.minipay.infrastructure.web.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    PlatformTransactionManager tm;

    public void toException() {
        var tmplt = new TransactionTemplate(tm);
        tmplt.executeWithoutResult(status -> {

        });
    }
}
