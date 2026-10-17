package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;

import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;

import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.*;
import java.util.stream.Collectors;

public class JavaApplication {
    public static void main(String[] args) {
        printUserInformation();     // 유저 서비스 CRUD 테스트 실행
        printChannelInformation();  // 채널 서비스 CRUD 테스트 실행
        printMessageInformation();  // 메시지 서비스 CRUD 테스트 실행
    }

    /*
     * 유저 CRUD 테스트를 위해 구현한 메서드입니다.
     */
    private static void printUserInformation(){

        System.out.println("####### 유저 CRUD 테스트 #######\n");
        /* 1. 유저 서비스 구현체를 생성한다. (상위 타입으로) */
        UserService userService = new JCFUserService();

        /* 2. User 객체를 생성하고 유저 이름을 등록한다. */
        User user1 = new User("최명하");
        User user2 = new User("이강한");
        User user3 = new User("박코딩");
        User user4 = new User("김자바");
        userService.createUser(user1); // "최명함" 유저 등록
        userService.createUser(user2); // "이강한" 유저 등록
        userService.createUser(user3); // "박코딩" 유저 등록
        userService.createUser(user4); // "김자바" 유저 등록

        System.out.println("=== 유저 생성 결과 ===");
        userService.readAllUser().stream()
                .forEach(user ->
                        System.out.println(user.getName() + "님이 생성되었습니다."));
        System.out.println("=".repeat(19));
        System.out.println();

        /* 3. 각각의 유저 정보를 단건으로 조회한다. */
        System.out.println("==== 단건 조회 ====");
        System.out.print(userService.readUser(user1.getId()));
        System.out.print(userService.readUser(user2.getId()));
        System.out.print(userService.readUser(user3.getId()));
        System.out.print(userService.readUser(user4.getId()));
        System.out.println("=".repeat(17));
        System.out.println();

        /* 4. 유저 이름을 수정한다. (수정 전과 수정 후) */
        // 수정 전 유저 이름 목록
        List<String> beforeNames = userService.readAllUser()
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());

        System.out.println("=== 수정 전 유저 이름 리스트 ===");
        System.out.println(String.join(", " , beforeNames));

        // 여기에서 각 유저의 새 이름을 직접 지정해 수정한다.
        userService.updateUser(user1.getId(), "최명함"); // "최명하" -> "최명함"
        userService.updateUser(user2.getId(), "이강함"); // "이강한" -> "이강함"

        // 수정 후 유저 이름 목록
        List<String> afterNames = userService.readAllUser()
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());

        System.out.println("=== 수정 후 유저 이름 리스트 ===");
        System.out.println(String.join(", " , afterNames));

        /* 5. 수정된 유저 데이터를 조회한다. */
        List<User> updatedUserInfo = userService.readAllUser().stream()
                .filter(user -> !beforeNames.contains(user.getName()))
                .collect(Collectors.toList());

        System.out.println("====== 수정된 유저 명단 ======");
        updatedUserInfo.stream()
                .forEach(user -> System.out.print(user));

        System.out.println("=".repeat(26));
        System.out.println();

        /* 6. 수정 이후 전체 유저를 조회한다 */
        System.out.println("=== 수정 이후 전체 유저 조회 ===");
        userService.readAllUser().stream()
                .forEach(user -> System.out.print(user));

        System.out.println("=".repeat(27));
        System.out.println();

        /* 7. 유저 정보를 삭제한다. */
        System.out.println("삭제 대상: \"" + user2.getName() + "\"");
        userService.deleteUser(user2.getId()); // 이강함 유저를 삭제합니다.

        /* 8. 삭제한 유저가 더 이상 조회되지 않는지 확인한다. */
        try {
            userService.readUser(user2.getId());
            System.out.println("삭제 확인 실패: \""
                    + user2.getName() + "\" 유저가 여전히 존재합니다.");
        } catch (IllegalArgumentException exception) {
            System.out.println("삭제 확인 성공: 삭제한 \""
                    + user2.getName() + "\" 유저가 더 이상 조회되지 않습니다.");
        }

        /* 9. 삭제 후 전체 유저를 조회한다. */
        System.out.println("=== 삭제 후 유저 목록 ===");
        userService.readAllUser().stream()
                .forEach(user ->
                        System.out.print(user));

        System.out.println("=".repeat(22));
        System.out.println();
    }

    /*
     * 채널 CRUD 테스트를 위해 구현한 메서드입니다.
     */
    private static void printChannelInformation() {

        System.out.println("####### 채널 CRUD 테스트 #######\n");
        /* 1. 채널 서비스 구현체를 생성한다. (상위 타입으로) */
        UserService userService = new JCFUserService();
        ChannelService channelService = new JCFChannelService(userService);

        /* 2. Channel 객체를 생성하고 채널 이름을 등록한다. */
        Channel channel1 = new Channel("공지사항");
        Channel channel2 = new Channel("자유게시판");
        Channel channel3 = new Channel("질문답변");
        channelService.createChannel(channel1); // "공지사항" 채널 등록
        channelService.createChannel(channel2); // "자유게시판" 채널 등록
        channelService.createChannel(channel3); // "질문답변" 채널 등록

        System.out.println("=== 채널 생성 결과 ===");
        channelService.readAllChannel().stream()
                .forEach(channel ->
                        System.out.println(channel.getName() + " 채널이 생성되었습니다."));
        System.out.println("=".repeat(19));
        System.out.println();

        /* 3. 각각의 채널 정보를 단건으로 조회한다. */
        System.out.println("==== 단건 조회 ====");
        System.out.print(channelService.readChannel(channel1.getId()));
        System.out.print(channelService.readChannel(channel2.getId()));
        System.out.print(channelService.readChannel(channel3.getId()));
        System.out.println("=".repeat(17));
        System.out.println();

        /* 4. 채널 이름을 수정한다. (수정 전과 수정 후) */
        // 수정 전 채널 이름 목록
        List<String> beforeNames = channelService.readAllChannel()
                .stream()
                .map(Channel::getName)
                .collect(Collectors.toList());

        System.out.println("=== 수정 전 채널 이름 리스트 ===");
        System.out.println(String.join(", ", beforeNames));

        // 여기에서 각 채널의 새 이름을 직접 지정해 수정한다.
        channelService.updateChannel(channel1.getId(), "공지");
        channelService.updateChannel(channel2.getId(), "자유");

        // 수정 후 채널 이름 목록
        List<String> afterNames = channelService.readAllChannel()
                .stream()
                .map(Channel::getName)
                .collect(Collectors.toList());

        System.out.println("=== 수정 후 채널 이름 리스트 ===");
        System.out.println(String.join(", ", afterNames));

        /* 5. 수정된 채널 데이터를 조회한다. */
        List<Channel> updatedChannels = channelService.readAllChannel().stream()
                .filter(channel -> !beforeNames.contains(channel.getName()))
                .collect(Collectors.toList());

        System.out.println("====== 수정된 채널 명단 ======");
        updatedChannels.stream()
                .forEach(channel -> System.out.print(channel));

        System.out.println("=".repeat(26));
        System.out.println();

        /* 6. 수정 이후 전체 채널을 조회한다. */
        System.out.println("=== 수정 이후 전체 채널 조회 ===");
        channelService.readAllChannel().stream()
                .forEach(channel -> System.out.print(channel));

        System.out.println("=".repeat(27));
        System.out.println();

        /* 7. 채널 정보를 삭제한다. */
        System.out.println("삭제 대상: \"" + channel2.getName() + "\"");
        channelService.deleteChannel(channel2.getId()); // 자유 채널을 삭제합니다.

        /* 8. 삭제한 채널이 더 이상 조회되지 않는지 확인한다. */
        try {
            channelService.readChannel(channel2.getId());
            System.out.println("삭제 확인 실패: \""
                    + channel2.getName() + "\" 채널이 여전히 존재합니다.");
        } catch (IllegalArgumentException exception) {
            System.out.println("삭제 확인 성공: 삭제한 \""
                    + channel2.getName() + "\" 채널이 더 이상 조회되지 않습니다.");
        }

        /* 9. 삭제 후 전체 채널을 조회한다. */
        System.out.println("=== 삭제 후 채널 목록 ===");
        channelService.readAllChannel().stream()
                .forEach(channel -> System.out.print(channel));

        System.out.println("=".repeat(22));
        System.out.println();
    }

    /*
     * 메시지 CRUD 테스트를 위해 구현한 메서드입니다.
     */
    private static void printMessageInformation() {

        System.out.println("####### 메시지 CRUD 테스트 #######\n");
        /* 1. 메시지 서비스 구현체를 생성한다. (상위 타입으로)  */
        UserService userService = new JCFUserService();
        ChannelService channelService = new JCFChannelService(userService);
        MessageService messageService = new JCFMessageService(userService, channelService);

        // 유저들을 채널에 참여시키기 위해 새로운 객체를 생성한다.
        User planner = new User("최명함");
        User backendDeveloper = new User("박코딩");
        User javaDeveloper = new User("이자바");
        Channel channelV1 = new Channel("개발 커뮤니티");

        // 유저들이 "개발 커뮤니티" 채널에 각각 서비스 등록을 한다.
        userService.createUser(planner);
        userService.createUser(backendDeveloper);
        userService.createUser(javaDeveloper);
        channelService.createChannel(channelV1);

        String beforeJoinContent = "참여 전에 작성한 메시지";

        // 채널에 참여하지 않은 "최명함" 의 메시지 생성을 시도한다.
        try {
            // 메시지 내용, 작성자 ID, 채널 ID를 전달해 메시지를 생성한다.
            Message rejectMessage = new Message(beforeJoinContent, planner.getId(), channelV1.getId());

            messageService.createMessage(rejectMessage);
            System.out.println("검사 실패 : 메시지가 생성되었습니다.");
        } catch (IllegalArgumentException exception){
            // 채널 미참여로 메시지 생성이 거부된 결과를 출력한다.
            System.out.println("\'" + beforeJoinContent +  "\' 생성 거부 : " + exception.getMessage());
        }

        // (최명함, 박코딩, 이자바)가 "개발 커뮤니티" 채널에 참여시킨다.
        System.out.println("입장 대상: \"" + planner.getName() + "\"");
        channelService.joinChannel(channelV1.getId(), planner.getId()); // 최명함 채널 참가.
        System.out.println("\'" + planner.getName() + "\' 유저가 \'"
                        + channelV1.getName() + "\' 채널에 입장했습니다.");

        System.out.println("입장 대상: \"" + backendDeveloper.getName() + "\"");
        // 박코딩 개발 커뮤니티 채널 참가
        channelService.joinChannel(channelV1.getId(), backendDeveloper.getId());
        System.out.println("\'" + backendDeveloper.getName() + "\' 유저가 \'"
                + channelV1.getName() + "\' 채널에 입장했습니다.");

        System.out.println("입장 대상: \"" + javaDeveloper.getName() + "\"");
        channelService.joinChannel(channelV1.getId(), javaDeveloper.getId());
        System.out.println("\'" + javaDeveloper.getName() + "\' 유저가 \'"
                + channelV1.getName() + "\' 채널에 입장했습니다.");

        System.out.println();

        /* 2. Message 객체를 생성하고 메시지 내용을 등록한다. */
        Message message1 = new Message("안녕하세요", planner.getId(), channelV1.getId());
        Message message2 = new Message("반갑습니다", planner.getId(), channelV1.getId());
        Message message3 = new Message("오늘 회의 안내입니다", planner.getId(), channelV1.getId());
        messageService.createMessage(message1);
        messageService.createMessage(message2);
        messageService.createMessage(message3);

        System.out.println("==== 개발 커뮤니티 입장 유저 목록 ====");
        String joinUserNames = channelV1.getMemberIds().stream()
                // 유저들을 단건으로 읽는다.
                .map(userService::readUser)
                // 유저들의 이름을 가져온다.
                .map(User::getName)
                .collect(Collectors.joining(", "));
        System.out.println(joinUserNames);
        System.out.println("=".repeat(31));
        System.out.println();

        // "최명하" 를 채널에 참여시킨 뒤 퇴장시킨다. 그리고 해당 채널에서 "최명하"의 참여 정보를 삭제한다.
        System.out.println("퇴장 대상 : \"" + planner.getName() + "\"");
        channelService.leaveChannel(channelV1.getId(), planner.getId());
        System.out.println("\"" + planner.getName() + "\" 유저가 \""
                + channelV1.getName() + "\" 채널에서 퇴장했습니다.");

        System.out.println("==== 개발 커뮤니티 퇴장 후 유저 목록 ====");
        String exitUserNames = channelV1.getMemberIds().stream()
                .map(userService::readUser)
                .map(User::getName)
                .collect(Collectors.joining(", "));

        System.out.println(exitUserNames);
        System.out.println("=".repeat(38));

        String afterLeaveContent = "퇴장 후 작성한 메시지";

        // 퇴장한 "최명하"의 메시지 생성을 시도한다.
        try {
            // 메시지 내용, 작성자 ID, 채널 ID를 전달해 메시지를 생성한다.
            Message rejectedMessage = new Message(afterLeaveContent, planner.getId(), channelV1.getId());

            messageService.createMessage(rejectedMessage);
            System.out.println("검사 실패 : 퇴장한 \"" + planner.getName() + "\"유저의 메시지가 생성되었습니다.");
        } catch (IllegalArgumentException exception){
            // 채널에서 퇴장했기 때문에 메시지 생성이 거부된 결과를 출력한다.
            System.out.println("검사 성공 : " + exception.getMessage());
        }

        System.out.println("\n=== 메시지 생성 결과 ===");
        messageService.readAllMessage().stream()
                .forEach(message ->
                        System.out.println
                                ("\"" +message.getContent()+ "\" 라는 내용을 생성하였습니다."));
        System.out.println("=".repeat(19));
        System.out.println();

        /* 3. 각각의 메시지 정보를 단건으로 조회한다. */
        System.out.println("==== 단건 조회 ====");
        System.out.print(messageService.readMessage(message1.getId()));
        System.out.print(messageService.readMessage(message2.getId()));
        System.out.print(messageService.readMessage(message3.getId()));
        System.out.println("=".repeat(17));
        System.out.println();

        /* 4. 메시지 내용을 수정한다. (수정 전과 수정 후) */
        List<String> beforeContents = messageService.readAllMessage()
                .stream()
                .map(Message::getContent)
                .collect(Collectors.toList());

        System.out.println("=== 수정 전 메시지 내용 리스트 ===");
        System.out.println(String.join(", ", beforeContents));

        // 여기에서 각 메시지의 새 내용을 직접 지정해 수정한다.
        messageService.updateMessage(message1.getId(), "안녕하세요!");
        messageService.updateMessage(message2.getId(), "반갑습니다!");

        // 수정 후 메시지 내용 목록
        List<String> afterContents = messageService.readAllMessage()
                .stream()
                .map(Message::getContent)
                .collect(Collectors.toList());

        System.out.println("=== 수정 후 메시지 내용 리스트 ===");
        System.out.println(String.join(", ", afterContents));

        /* 5. 수정된 메시지 데이터를 조회한다. */
        List<Message> updatedMessages = messageService.readAllMessage().stream()
                .filter(message -> !beforeContents.contains(message.getContent()))
                .collect(Collectors.toList());

        System.out.println("====== 수정된 메시지 명단 ======");
        updatedMessages.stream()
                .forEach(message -> System.out.print(message));

        System.out.println("=".repeat(26));
        System.out.println();

        /* 6. 수정 후 전체 메시지 목록을 조회한다. */
        System.out.println("=== 수정 이후 전체 메시지 조회 ===");
        messageService.readAllMessage().stream()
                .forEach(message -> System.out.print(message));

        System.out.println("=".repeat(27));
        System.out.println();

        /* 7. 메시지를 삭제한다. */
        System.out.println("삭제할 메시지: \"" + message2.getContent() + "\"");
        messageService.deleteMessage(message2.getId());

        /* 8. 삭제한 메시지가 더 이상 조회되지 않는지 확인한다. */
        try {
            messageService.readMessage(message2.getId());
            System.out.println("삭제 확인 실패: \""
                    + message2.getContent() + "\" 라는 메시지가 여전히 존재합니다.");
        } catch (IllegalArgumentException exception) {
            System.out.println("삭제 확인 성공: \""
                    + message2.getContent() + "\" 라는 메시지가 더 이상 조회되지 않습니다.");
        }

        /* 9. 삭제 후 전체 메시지를 조회한다. */
        System.out.println("=== 삭제 후 메시지 목록 ===");
        messageService.readAllMessage().stream()
                .forEach(message -> System.out.print(message));

        System.out.println("=".repeat(22));
    }
}