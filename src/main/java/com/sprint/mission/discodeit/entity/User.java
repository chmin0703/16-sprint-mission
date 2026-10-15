package com.sprint.mission.discodeit.entity;

import java.util.UUID;

// User 클래스는 BaseEntity 클래스로부터 상속한다.
public class User extends BaseEntity {
    // 유저의 필드 선언
    private String name;

    // User 객체를 생성할 때 공통 필드와 사용자 이름을 전달받는다.
    public User(UUID id, Long createAt, Long updateAt, String name) {
        super(id, createAt, updateAt); // 부모 클래스 생성자 호출하여 공통 필드 초기화.
        this.name = name; // 전달받은 사용자 이름을 현재 객체의 필드 name에 저장한다.
    }

    // 저장된 사용자 이름을 반환하는 Getter 메서드입니다.
    public String getName() {
        return name;
    }

    // 전달받은 유저 이름으로 현재 객체의 이름을 변경한다.
    public void changeName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{id:'" + getId() + "'},{" + "name:'" + name + "'}, {createAt: "
                + getCreateAt() + " },{updateAt: " + getUpdateAt() + " }\n";
    }
}