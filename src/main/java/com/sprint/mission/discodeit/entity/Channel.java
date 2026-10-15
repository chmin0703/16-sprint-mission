package com.sprint.mission.discodeit.entity;

import java.util.UUID;

// Channel 클래스는 BaseEntity 클래스로부터 상속한다.
public class Channel extends BaseEntity {
    // 채널의 필드 선언.
    private String name;

    // Channel 객체를 생성할 때 채널 이름을 전달받는다.
    public Channel(String name) {
        super(); // 부모 클래스 생성자에서 ID와 생성·수정 시각을 초기화한다.
        this.name = name; // 전달받은 채널 이름을 필드에 저장한다.
    }

    // 저장된 채널 이름을 반환하는 Getter 메서드입니다.
    public String getName() {
        return name;
    }

    // 전달받은 채널 이름으로 현재 객체의 이름을 변경한다.
    public void changeName(String name){
        // 이름이 null 이거나 공백이면 예외 처리한다.
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("채널 이름은 필수입니다.");
        }
        this.name = name;
        touch(); // 수정 시각(updateAt)을 현재 시각으로 갱신한다.
    }

    @Override
    public String toString() {
        return "Channel{id:'" + getId() + "'},{" + "name:'" + name + "'}, {createAt: "
                + getCreateAt() + " },{updateAt: " + getUpdateAt() + " }\n";
    }
}