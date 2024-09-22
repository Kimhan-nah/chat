package com.anchoreer.chat.application.port.in;

import com.anchoreer.chat.domain.entity.ChatRoom;

public interface ChatRoomManagementUseCase {
    ChatRoom createRoom(Long userId, String title);

    void deleteRoom(Long roomId);

    void joinRoom(Long userId, Long roomId);

    void leaveRoom(Long userId, Long roomId);
}
