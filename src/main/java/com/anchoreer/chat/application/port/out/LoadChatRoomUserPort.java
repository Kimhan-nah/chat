package com.anchoreer.chat.application.port.out;

import java.util.Optional;

import com.anchoreer.chat.domain.entity.ChatRoom;
import com.anchoreer.chat.domain.entity.ChatRoomUser;
import com.anchoreer.chat.domain.entity.User;

public interface LoadChatRoomUserPort {
    Optional<ChatRoomUser> load(User user, ChatRoom chatRoom);
}
