package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.UUID;

public class JavaApplication {
    public static void main(String[] args) {

        printUserInformation(); // 유저 서비스 CRUD 테스트 실행
    }

    public static void printUserInformation(){
        // 1. 서비스 구현체를 생성한다.
        JCFUserService userService = new JCFUserService();

        // 2. User 객체 생성에 필요한 값을 각각 준비한다.
        UUID idV1 = UUID.randomUUID();
        UUID idV2 = UUID.randomUUID();
        Long createAt = System.currentTimeMillis();
        Long updateAt = System.currentTimeMillis();

        // 3. User 객체를 생성.
        User user1 = new User(idV1, createAt, updateAt, "최명철");
        User user2 = new User(idV2, createAt, updateAt, "이강한");

        // 4. 유저 등록.
        userService.createUser(user1); // 유저 등록
        userService.createUser(user2);

        // 5. 단건/다건 조회.
        System.out.print("단건 조회: "+userService.readUser(idV1));
        System.out.print("단건 조회: "+userService.readUser(idV2));
        System.out.println();

        System.out.println("전체 조회: ");
        for(User userInfo : userService.readAllUser()){
            System.out.print(userInfo);
        }

        // 6. 유저 정보 수정.
        userService.updateUser(idV1, "최명호" ); // 수정

        // 7. 수정된 유저 데이터 조회.
        User updatedUserInfo = userService.readUser(idV1);
        System.out.println("수정된 이름: " + updatedUserInfo); // 수정된 데이터 조회

        // 8. 유저 정보를 올바르게 삭제 되었는지 확인.
        userService.deleteUser(idV1);
        try{
            userService.readUser(idV1);
            System.out.println("삭제 확인 실패: 유저가 여전히 존재합니다.");
        } catch (IllegalArgumentException exception){
            System.out.println("삭제 확인 성공: 유저를 찾을 수 없습니다.");
        }
        System.out.println();
        for(User DeletedUserInfo : userService.readAllUser()){
            System.out.print("삭제 후 유저 목록: " + DeletedUserInfo);
        }
    }
}
