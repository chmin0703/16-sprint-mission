package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public abstract class BaseEntity {
    // 필드
    private final UUID id;
    private final Long createAt;
    private final Long updateAt;

    // 생성자 초기화
    public BaseEntity(UUID id, Long createAt, Long updateAt) {
        this.id = id; // -> 전달받은 id를 필드에 저장.
        this.createAt = createAt; // 생성 시각 저장.
        this.updateAt = updateAt; // 수정 시각 저장.
    }

    // Getter 함수 정의(필드에 저장한 값을 반환함)
    public UUID getId() {
        return id;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }
}
