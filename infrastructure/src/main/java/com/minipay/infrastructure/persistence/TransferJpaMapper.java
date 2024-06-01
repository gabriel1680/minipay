package com.minipay.infrastructure.persistence;

import com.minipay.domain.Transfer;

public class TransferJpaMapper {
    public static Transfer toDomain(TransferJpaEntity entity) {
        return new Transfer(
                entity.getId(),
                entity.getPayerId(),
                entity.getPayerId(),
                entity.getAmount()
        );
    }

    public static TransferJpaEntity toPersistence(Transfer model) {
        return new TransferJpaEntity(
                model.getId(),
                model.getPayerId(),
                model.getPayeeId(),
                model.getAmount());
    }
}
