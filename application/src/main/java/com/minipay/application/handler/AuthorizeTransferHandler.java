package com.minipay.application.handler;

import com.minipay.application.service.AuthorizationService;
import com.minipay.domain.event.EventHandler;
import com.minipay.domain.transfer.TransferCreated;

public class AuthorizeTransferHandler implements EventHandler<TransferCreated> {
    private final AuthorizationService authorizationService;

    public AuthorizeTransferHandler(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public boolean canHandle(Class<?> event) {
        return event.equals(TransferCreated.class);
    }

    @Override
    public void handle(TransferCreated event) {
        if (!authorizationService.authorize(event.amount())) {
            System.out.println("Not authorized operation!");
        } else {
            System.out.println("Operation authorized!");
        }
    }
}
