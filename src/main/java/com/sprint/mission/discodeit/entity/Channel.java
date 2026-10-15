package com.sprint.mission.discodeit.entity;

import java.util.UUID;

// Channel 클래스는 BaseEntity 클래스로부터 상속한다.
public class Channel extends BaseEntity {
    // 채널의 필드 선언.
    private String name;

    // Channel 객체를 생성할 때 공통 필드와 채널 이름을 전달받는다.
    public Channel(UUID id, Long createAt, Long updateAt, String name) {
        super(id, createAt, updateAt); // 부모 클래스 생성자 호출하여 공통 필드 초기화.
        this.name = name; // 전달받은 채널 이름을 현재 객체의 필드 name에 저장한다.
    }

    // 저장된 채널 이름을 반환하는 Getter 메서드입니다.
    public String getName() {
        return name;
    }

    // 전달받은 채널 이름으로 현재 객체의 이름을 변경한다.
    public void changeName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{id:'" + getId() + "'},{" + "name:'" + name + "'}, {createAt: "
                + getCreateAt() + " },{updateAt: " + getUpdateAt() + " }\n";
    }
}