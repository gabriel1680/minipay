package com.minipay.domain;

import java.util.Optional;

public interface UserRepository {
    Integer getNextId();
    void save(User user);
    void remove(User user);
    Optional<User> get(Integer userId);
    boolean exists(Integer id);
    boolean exists(String email);
}
