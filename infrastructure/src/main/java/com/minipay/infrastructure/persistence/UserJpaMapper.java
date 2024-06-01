package com.minipay.infrastructure.persistence;

import com.minipay.domain.User;
import com.minipay.domain.UserFactory;

public class UserJpaMapper {
    public static User toDomain(UserJpaEntity entity) {
        return UserFactory.create(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getDocumentType(),
                entity.getDocumentValue()
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
                document.value()
        );
    }
}
