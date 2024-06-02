package com.minipay.infrastructure.persistence.transfer;

import com.minipay.infrastructure.web.api.dto.TransferViewResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransferJpaRepository extends JpaRepository<TransferJpaEntity, UUID> {
    @Query(
            value = """
            SELECT t.id, u.name as "payeeName", u.email as "payeeEmail", t.amount FROM transfers as t
            LEFT JOIN users as u ON t.payee_id = u.id
            WHERE t.payer_id = uuid(?1)
            """,
            nativeQuery = true
    )
    List<TransferViewResponse> getAllTransfersOf(String userId);
}
