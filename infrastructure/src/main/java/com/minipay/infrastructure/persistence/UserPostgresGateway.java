package com.minipay.infrastructure.persistence;

import com.minipay.domain.User;
import com.minipay.domain.UserRepository;

import java.util.Optional;
import java.util.UUID;

public class UserPostgresGateway implements UserRepository  {

    private final UserJpaRepository jpaRepository;

    public UserPostgresGateway(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(User user) {
        jpaRepository.save(UserJpaMapper.toPersistence(user));
    }

    @Override
    public void remove(User user) {
        jpaRepository.delete(UserJpaMapper.toPersistence(user));
    }

    @Override
    public Optional<User> get(UUID userId) {
        return jpaRepository
                .findById(userId.toString())
                .map(UserJpaMapper::toDomain);
    }

    @Override
    public boolean exists(UUID id) {
        return jpaRepository.existsById(id.toString());
    }

    @Override
    public boolean exists(String email) {
        return jpaRepository.existsByEmail(email);
    }
}
