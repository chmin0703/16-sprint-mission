package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.repository.file.FileUserRepository;
import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileMessageRepository;

import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;

import com.sprint.mission.discodeit.repository_file_consoleRun.ReFileUserConsoleRun;
import com.sprint.mission.discodeit.repository_file_consoleRun.ReFileChannelConsoleRun;
import com.sprint.mission.discodeit.repository_file_consoleRun.ReFileMessageConsoleRun;

import com.sprint.mission.discodeit.service.file.FileChannelService;
import com.sprint.mission.discodeit.service.file.FileMessageService;
import com.sprint.mission.discodeit.service.file.FileUserService;

public class JavaApplicationVer4 {
    public static void main(String[] args) {
        // UserRepository userService = new FileUserRepository();
        // ChannelRepository channelService = new FileChannelRepository();
        // MessageRepository messageService = new FileMessageRepository();

        ReFileUserConsoleRun.printUserRepositoryInformation();
//        ReFileUserConsoleRun.printUserRepositoryInformation();
//        ReFileUserConsoleRun.printUserRepositoryInformation();
    }
}
