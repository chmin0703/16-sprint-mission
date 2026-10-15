package com.sprint.mission.discodeit.entity;

import java.util.UUID;

// Message 클래스는 BaseEntity 클래스로부터 상속한다.
public class Message extends BaseEntity {
    // 메시지의 필드 선언.
    private String content;

    // Message 객체를 생성할 때 공통 필드와 메시지 내용을 전달받는다.
    public Message(UUID id, Long createAt, Long updateAt, String content) {
        super(id, createAt, updateAt); // 부모 클래스 생성자 호출하여 공통 필드 초기화.
        this.content = content; // 전달받은 메시지 내용을 현재 객체의 필드 content에 저장한다.
    }

    // 저장된 메시지 내용을 반환하는 Getter 메서드입니다.
    public String getContent() {
        return content;
    }

    // 전달받은 메시지 내용으로 현재 객체의 내용을 변경한다.
    public void changeContent(String content){
        this.content = content;
    }
}