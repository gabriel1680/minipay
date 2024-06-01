package com.minipay.infrastructure.persistence.transfer;

import com.minipay.domain.transfer.Transfer;
import com.minipay.domain.transfer.TransferRepository;
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
