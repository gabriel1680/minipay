package com.minipay.domain;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);
    void remove(User user);
    Optional<User> get(UUID userId);
    boolean exists(UUID id);
    boolean exists(String email);
}
