package com.minipay.infrastructure.persistence.transfer;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Transfer")
@Table(name = "transfers")
public class TransferJpaEntity {
    @Id
    private UUID id;

    @Column(name = "payer_id")
    private UUID payerId;

    @Column(name = "payee_id")
    private UUID payeeId;

    @Column(name = "amount")
    private BigDecimal amount;

    public TransferJpaEntity(UUID id, UUID payerId, UUID payeeId, BigDecimal amount) {
        this.id = id;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferJpaEntity that = (TransferJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public TransferJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPayerId() {
        return payerId;
    }

    public void setPayerId(UUID payerId) {
        this.payerId = payerId;
    }

    public UUID getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(UUID payeeId) {
        this.payeeId = payeeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
