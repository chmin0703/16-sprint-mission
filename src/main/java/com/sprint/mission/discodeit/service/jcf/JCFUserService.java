package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JCFUserService implements UserService {
    private final List<User> data;

    public JCFUserService(){
        this.data = new ArrayList<>();
    }

    @Override
    public User createUser(User user) {
        for(User targetUser : data){
            // 생성할 유저 이름과 기존에 있는 이름이 중복되는지 검사한다.
            if(targetUser.getName().equals(user.getName())){
                throw new IllegalArgumentException("중복된 이름입니다.");
            }
        }
        data.add(user); // 전달받은 유저를 목록에 추가한다.
        return user; // 추가한 유저 객체를 반환한다.
    }

    @Override
    public User readUser(UUID id) {
        for(User user : data){
            if(user.getId().equals(id)){
                // 해당 유저 ID가 요청받은 ID와 일치하면 user를 반환한다.
                return user;
            }
        }
        // 해당 유저를 찾지 못하면 예외 발생시킨다.
        throw new IllegalArgumentException("유저를 찾을 수 없습니다");
    }

    @Override
    public List<User> readAllUser() {
        // data 에 저장된 유저들을 담은 새 목록을 반환한다.
        return new ArrayList<>(data);
    }

    @Override
    public User updateUser(UUID id, String name) {
        // 수정할 유저를 찾는다.
        User targetUser = readUser(id);
        // 기존의 이름과 변경할 이름이 같은지 검사한다.
        if(targetUser.getName().equals(name)){
            // 기존의 이름과 동일하면 예외를 발생시킨다.
            throw new IllegalArgumentException("기존 이름과 동일합니다");
        }
        // 다른 유저가 이미 사용 중인 이름인지 검사한다.
        for(User otherUser : data){
            if(otherUser != targetUser && otherUser.getName().equals(name)){
                throw new IllegalArgumentException("중복된 이름입니다.");
            }
        }
        targetUser.changeName(name); // 모든 검사가 끝나면 유저의 이름을 변경한다.
        return targetUser;  // 이름을 변경한 유저 객체를 반환한다.
    }

    @Override
    // 메인 클래스에서 deleteUser() 메서드가 호출되어 id를 받는다.
    public void deleteUser(UUID id) {
        // data에 있는 유저 목록을 조회하여 반복한다.
        for(int i = 0; i < data.size(); i++){
            // i번째 유저의 ID와 삭제하려는 유저 ID가 같은지 확인한다.
            if (data.get(i).getId().equals(id)){

                data.remove(i); // 해당 인덱스의 유저를 삭제한다.
                return; // 삭제가 완료되면 메서드를 종료한다.
            }
        }
        // 해당 유저를 찾지 못하면 예외를 발생시킨다.
        throw new IllegalArgumentException("삭제할 유저를 찾을 수 없습니다.");
    }
}