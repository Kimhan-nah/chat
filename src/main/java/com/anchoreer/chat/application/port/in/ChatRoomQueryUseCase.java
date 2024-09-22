package com.anchoreer.chat.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anchoreer.chat.domain.entity.Chat;
import com.anchoreer.chat.domain.entity.ChatRoomDto;

public interface ChatRoomQueryUseCase {
    Page<Chat> getChatsByRoomId(Long roomId, Pageable pageable);

    Page<ChatRoomDto> getChatRooms(Pageable pageable);
}
