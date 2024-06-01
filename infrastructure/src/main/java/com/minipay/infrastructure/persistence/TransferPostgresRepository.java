package com.minipay.infrastructure.persistence;

import com.minipay.domain.Transfer;
import com.minipay.domain.TransferRepository;
import org.springframework.stereotype.Component;

@Component
public class TransferPostgresRepository implements TransferRepository {
    private final TransferJpaRepository jpaRepository;

    public TransferPostgresRepository(TransferJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Transfer transfer) {
        jpaRepository.save(TransferJpaMapper.toPersistence(transfer));
    }
}
