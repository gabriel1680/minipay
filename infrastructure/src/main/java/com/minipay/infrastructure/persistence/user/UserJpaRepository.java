package com.minipay.infrastructure.persistence.user;

import com.minipay.infrastructure.web.api.dto.UserViewResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    boolean existsByEmail(String email);
    @Query(value = """
        SELECT id, name, email, document_type as "documentType",
        document_value as "documentValue", balance
        FROM users;
    """, nativeQuery = true)
    List<UserViewResponse> getAll();
}
