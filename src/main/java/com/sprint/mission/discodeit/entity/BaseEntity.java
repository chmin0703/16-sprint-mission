package com.sprint.mission.discodeit.entity;

import java.io.Serializable;
import java.util.UUID;

// User, Channel, Message는 BaseEntity를 상속하므로 파일 직렬화가 가능해진다.
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // 객체의 고유 ID와 생성 시각, 수정 시각을 저장하는 필드
    private final UUID id;
    private final Long createdAt;
    private Long updatedAt;

    // 객체 생성 시 고유 ID와 생성 시각을 초기화하고,
    // 수정 시각은 생성 시각과 동일하게 설정한다.
    protected BaseEntity() {
        this.id = UUID.randomUUID(); // 랜덤한 UUID를 생성하여 고유 ID로 설정한다.
        this.createdAt = System.currentTimeMillis(); // 객체의 생성 시각을 설정한다.
        this.updatedAt = this.createdAt; // 수정 시각을 생성 시각과 동일하게 설정한다.
    }

    // 기존 객체의 ID와 생성·수정 시각을 그대로 복사한다.
    protected BaseEntity(BaseEntity source) {
        this.id = source.id; // 기존 객체의 고유 ID를 복사한다.
        this.createdAt = source.createdAt; // 기존 객체의 생성 시각을 복사한다.
        this.updatedAt = source.updatedAt; // 기존 객체의 수정 시각을 복사한다.
    }

    // Getter 함수 정의(필드에 저장한 값을 반환함)
    // 객체의 고유 ID를 반환한다.
    public UUID getId() {
        return id;
    }

    // 객체의 생성 시각을 반환한다.
    public Long getCreatedAt() {
        return createdAt;
    }

    // 객체의 수정 시각을 반환한다.
    public Long getUpdatedAt() {
        return updatedAt;
    }

    // 객체의 수정 시각을 현재 시각으로 갱신한다.
    public void touch(){
        this.updatedAt = System.currentTimeMillis();
    }
}