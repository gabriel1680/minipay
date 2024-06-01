package com.minipay.infrastructure.persistence.user;

import com.minipay.domain.user.User;
import com.minipay.domain.user.UserCredentials;
import com.minipay.domain.user.UserDocument;

public class UserJpaMapper {
    public static User toDomain(UserJpaEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                new UserCredentials(entity.getEmail(), entity.getPassword()),
                new UserDocument(entity.getDocumentType(), entity.getDocumentValue()),
                entity.getBalance()
        );
    }

    public static UserJpaEntity toPersistence(User model) {
        final var credentials = model.getCredentials();
        final var document = model.getDocument();
        return new UserJpaEntity(
                model.getId(),
                model.getName(),
                model.getType(),
                credentials.email(),
                credentials.password(),
                document.type(),
                document.value(),
                model.getBalance()
        );
    }
}
