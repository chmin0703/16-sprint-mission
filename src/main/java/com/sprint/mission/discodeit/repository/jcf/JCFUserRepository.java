package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

public class JCFUserRepository implements UserRepository {
    private final List<User> data = new ArrayList<>();

    @Override
    public User createUser(User user) {
        data.add(user);
        return user;
    }

    @Override
    public User updateUser(User user) {

        // 0부터 data 길이까지 범위를 정한다.
        int index = IntStream.range(0, data.size())
                // 해당 ID와 유저 ID가 같은지 확인한다.
                .filter(i -> data.get(i).getId().equals(user.getId()))
                .findFirst()
                // 조건에 해당하지 않으면 예외 처리를 시킨다.
                .orElseThrow(() ->
                        new IllegalArgumentException("수정할 채널을 찾을 수 없습니다."));

        data.set(index, user);
        return user;
    }

    @Override
    public Optional<User> findByUserId(UUID id) {
        // 첫 번째로 찾은 ID와 해당 ID가 맞는기 검사한다.
        return data.stream()
                .filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public boolean existsByUserId(UUID id) {
        return data.stream().anyMatch(c -> c.getId().equals(id));
    }

    @Override
    public void deleteByUserId(UUID id) {
        data.removeIf(c -> c.getId().equals(id));
    }
}
