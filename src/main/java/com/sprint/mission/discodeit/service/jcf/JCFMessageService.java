package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JCFMessageService implements MessageService {
    private final List<Message> data;

    public JCFMessageService() {
        this.data = new ArrayList<>();
    }

    @Override
    public Message createMessage(Message message) {
        data.add(message); // 전달받을 메시지를 목록에 추가한다.
        return message; // 추가한 메시지 객체를 반환한다.
    }

    @Override
    public Message readMessage(UUID id) {
        for (Message message : data) {
            if (message.getId().equals(id)) {
                // 메시지의 ID가 요청받은 ID와 일치하면 해당 message를 반환한다.
                return message;
            }
        }
        // 해당 ID와 일치하는 메시지를 찾지 못하면 예외를 발생시킨다.
        throw new IllegalArgumentException("메시지를 찾을 수 없습니다");
    }

    @Override
    public List<Message> readAllMessage() {
        // data 에 저장된 메시지를 담은 새 목록을 반환한다.
        return new ArrayList<>(data);
    }

    @Override
    public Message updateMessage(UUID id, String content) {
        // 수정할 메시지를 찾는다.
        Message targetMessage = readMessage(id);
        // 기존 메시지 내용과 수정할 내용이 동일한지 확인한다.
        if (targetMessage.getContent().equals(content)){
            throw new IllegalArgumentException("기존 메시지 내용과 동일합니다.");
        }
        targetMessage.changeContent(content); // 내용이 다를 때만 변경한다.
        return targetMessage;   // 내용을 변경한 메시지 객체를 반환한다.
    }

    @Override
    // 메인 클래스에서 deleteMessage() 메서드가 호출되어 id를 받는다.
    public void deleteMessage(UUID id) {
        // data에 있는 메시지 목록을 조회하여 반복한다.
        for (int i = 0; i < data.size(); i++) {
            // i번째 메시지의 ID와 삭제하려는 메시지 ID가 같은지 확인한다.
            if (data.get(i).getId().equals(id)) {

                data.remove(i); // 해당 인덱스의 메시지를 삭제한다.
                return; // 삭제가 완료되면 메서드를 종료한다.
            }
        }
        // 삭제할 메시지를 찾지 못하면 예외 발생시킨다.
        throw new IllegalArgumentException("삭제할 메시지를 찾을 수 없습니다.");
    }
}