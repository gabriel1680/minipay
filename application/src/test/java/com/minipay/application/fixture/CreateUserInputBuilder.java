package com.minipay.application.fixture;

import com.minipay.application.CreateUserUseCase;

public class CreateUserInputBuilder {

    private String name = "John Doe";
    private String email = "john@doe.com";
    private String password = "123";
    private String documentType = "cpf";
    private String documentValue = "97088969009";

    private CreateUserInputBuilder() {}

    public static CreateUserInputBuilder anInput() {
        return new CreateUserInputBuilder();
    }

    public CreateUserInputBuilder withEmail(String aEmail) {
        email = aEmail;
        return this;
    }

    public CreateUserUseCase.Input build() {
        return new CreateUserUseCase.Input(
                name,
                email,
                password,
                documentType,
                documentValue
        );
    }
}
