package com.minipay.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transfers")
public class TransferJpaEntity {
    @Id
    public UUID id;

    @Column(name = "payer_id")
    public String payerId;

    @Column(name = "payee_id")
    public String payeeId;

    @Column(name = "amount")
    public BigDecimal amount;
}
