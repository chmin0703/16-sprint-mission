package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User createUser(User user);
    User updateUser(User user);
    Optional<User> findByUserId(UUID id);
    List<User> findAll();
    boolean existsByUserId(UUID id);
    void deleteByUserId(UUID id);
}
