package com.anchoreer.chat.application.port.in;

import com.anchoreer.chat.domain.entity.ChatRoom;
import com.anchoreer.chat.domain.entity.User;

public interface ChatRoomCommandUseCase {
    ChatRoom createRoom(String title);

    void deleteRoom(ChatRoom chatRoom);

    void joinRoom(User user, ChatRoom chatRoom);

    void leaveRoom(User user, ChatRoom chatRoom);
}
