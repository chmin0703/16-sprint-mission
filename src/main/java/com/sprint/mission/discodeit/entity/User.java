package com.sprint.mission.discodeit.entity;

// User 클래스는 BaseEntity 클래스로부터 상속한다.
public class User extends BaseEntity {
    // 유저의 필드 선언
    private String name;

    public User(String name) {
        super(); // 부모 클래스의 기본 생성자를 호출하여 ID와 생성·수정 시각을 초기화한다.

        // 이름이 null이거나 공백이면 예외를 발생시킨다.
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("사용자 이름은 필수입니다.");
        }

        // 유효성 검사를 통과한 이름을 필드에 저장한다.
        this.name = name;
    }

    public User(User source, String name) {
        // 부모 클래스의 복사 생성자를 호출하여 기존 유저의 ID와 생성·수정 시각을 복사한다.
        super(source);

        // 사용자 이름을 생성할 때 null값 이거나 공백일때 예외 처리하는 검사입니다.
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("사용자 이름은 필수입니다.");
        }
        // 유효성 검사를 통과한 이름을 필드에 저장한다.
        this.name = name;

        // 수정 시각을 현재 시각으로 갱신한다.
        touch();
    }

    // 저장된 사용자 이름을 반환하는 Getter 메서드입니다.
    public String getName() {
        return name;
    }

    // 전달받은 유저 이름으로 현재 객체의 이름을 변경한다.
    public void changeName(String name){
        // 이름이 null 이거나 공백이면 예외 처리한다.
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("사용자 이름은 필수입니다.");
        }
        this.name = name;
        touch(); // 수정 시각(updatedAt)을 현재 시각으로 갱신한다.
    }

    @Override
    public String toString() {
        return "User{id:'" + getId() + "'},{" + "name:'" + name + "'},{createdAt: "
                + getCreatedAt() + " },{updatedAt: " + getUpdatedAt() + " }\n";
    }
}