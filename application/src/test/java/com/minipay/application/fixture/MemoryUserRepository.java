package com.minipay.application.fixture;

import com.minipay.domain.user.User;
import com.minipay.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MemoryUserRepository implements UserRepository {
    public List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void remove(User user) {
        users.remove(user);
    }

    @Override
    public Optional<User> get(UUID userId) {
        return users.stream().filter((final User aUser) -> aUser.getId().equals(userId)).findFirst();
    }

    @Override
    public boolean exists(UUID id) {
        return users
                .stream()
                .anyMatch((User aUser) -> aUser.getId().equals(id));
    }

    @Override
    public boolean exists(String email) {
        return users
                .stream()
                .anyMatch((User aUser) -> aUser.getCredentials().email().equals(email));
    }
}
