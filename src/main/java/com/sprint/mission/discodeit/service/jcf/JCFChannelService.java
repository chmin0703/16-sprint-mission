package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JCFChannelService implements ChannelService {
    private final List<Channel> data;

    public JCFChannelService() {
        this.data = new ArrayList<>();
    }

    @Override
    public Channel createChannel(Channel channel) {
        for(Channel targetChannel : data){
            // 생성할 채널명과 기존에 있는 채널명이 중복되는지 검사한다.
            if(targetChannel.getName().equals(channel.getName())){
                throw new IllegalArgumentException("중복된 채널 이름입니다");
            }
        }
        data.add(channel); // 전달받은 채널을 목록에 추가한다.
        return channel; // 추가한 채널 객체를 반환한다.
    }

    @Override
    public Channel readChannel(UUID id) {
        for(Channel channel : data){
            if(channel.getId().equals(id)){
                // 해당 채널 ID를 가져와 요청받은 ID와 일치하면 channel 을 반환한다.
                return channel;
            }
        }
        // 해당 채널을 찾지 못하면 예외 발생사칸다.
        throw new IllegalArgumentException("채널을 찾을 수 없습니다");
    }

    @Override
    public List<Channel> readAllChannel() {
        // data 에 저장된 채널들을 담은 새 목록을 반환한다.
        return new ArrayList<>(data);
    }

    @Override
    public Channel updateChannel(UUID id, String name) {
        // 수정할 채널을 찾는다.
        Channel targetChannel = readChannel(id);
        // 기존의 채널명과 변경할 채널 이름이 같은지 검사한다.
        if(targetChannel.getName().equals(name)){
            // 기존의 채널 이름과 동일하면 예외를 발생시킨다.
            throw new IllegalArgumentException("기존 채널명과 동일합니다.");
        }
        // 다른 채널이 이미 사용 중인 채널 이름인지 검사한다.
        for(Channel otherChannel : data){
            if(otherChannel.getName().equals(name)){
                throw new IllegalArgumentException("중복된 채널명입니다.");
            }
        }
        // 모든 검사가 끝나면 채널 이름을 변경한다.
        targetChannel.changeName(name);
        return targetChannel; // 이름을 변경한 채널 객체를 반환한다.
    }

    @Override
    // 메인 클래스에서 deleteChannel() 메서드가 호출되어 id를 받는다.
    public void deleteChannel(UUID id) {
        // data에 있는 채널 목록을 조회하여 반복한다.
        for(int i = 0; i < data.size(); i++){
            // i번째 채널의 ID와 삭제하려는 채널 id가 일치하는지 확인한다.
            if(data.get(i).getId().equals(id)){

                data.remove(i); // 해당 인덱스의 채널을 삭제한다.
                return; // 삭제가 완료되면 메서드를 종료한다.
            }
        }
        // 해당 채널을 찾지 못하면 예외를 발생시킨다.
        throw new IllegalArgumentException("삭제할 채널을 찾을 수 없습니다.");
    }
}