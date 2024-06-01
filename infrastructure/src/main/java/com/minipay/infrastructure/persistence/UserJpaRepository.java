package com.minipay.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    boolean existsByEmail(String email);
}
