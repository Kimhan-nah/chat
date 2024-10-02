package com.anchoreer.chat.application.port.in;

import com.anchoreer.chat.domain.entity.Chat;
import com.anchoreer.chat.domain.entity.ChatRoom;
import com.anchoreer.chat.domain.entity.User;

public interface MessageCommandUseCase {
    Chat sendMessage(ChatRoom chatRoom, User sender, String message);

    void deleteMessage(ChatRoom chatRoom, User sender, Chat chat);
}
