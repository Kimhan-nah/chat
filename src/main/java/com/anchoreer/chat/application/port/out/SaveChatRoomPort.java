package com.anchoreer.chat.application.port.out;

import com.anchoreer.chat.domain.entity.ChatRoom;

public interface SaveChatRoomPort {
    ChatRoom save(ChatRoom chatRoom);
}
