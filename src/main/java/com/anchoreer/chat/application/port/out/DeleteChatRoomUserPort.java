package com.anchoreer.chat.application.port.out;

import com.anchoreer.chat.domain.entity.ChatRoomUser;

public interface DeleteChatRoomUserPort {
    void delete(ChatRoomUser chatRoomUser);
}
