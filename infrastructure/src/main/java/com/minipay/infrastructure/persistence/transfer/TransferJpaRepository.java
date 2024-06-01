package com.minipay.infrastructure.persistence.transfer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferJpaRepository extends JpaRepository<TransferJpaEntity, UUID> {
}
