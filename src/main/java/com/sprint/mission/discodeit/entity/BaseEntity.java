package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public abstract class BaseEntity {

    // 객체의 고유 ID와 생성 시각, 수정 시각을 저장하는 필드
    private final UUID id;
    private final Long createAt;
    private Long updateAt;

    // 객체 생성 시 ID와 생성 시각을 초기화하고,
    // 수정 시각은 생성 시각과 동일하게 설정한다.
    protected BaseEntity() {
        this.id = UUID.randomUUID(); // --> 랜덤한 UUID 값을 생성한다.
        this.createAt = System.currentTimeMillis();
        this.updateAt = this.createAt;
    }

    // Getter 함수 정의(필드에 저장한 값을 반환함)
    // 객체의 고유 ID를 반환한다.
    public UUID getId() {
        return id;
    }

    // 객체의 생성 시각을 반환한다.
    public Long getCreateAt() {
        return createAt;
    }

    // 객체의 수정 시각을 반환한다.
    public Long getUpdateAt() {
        return updateAt;
    }

    // 객체의 수정 시각을 현재 시각으로 갱신한다.
    public void touch(){
        this.updateAt = System.currentTimeMillis();
    }
}
