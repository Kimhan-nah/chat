package com.anchoreer.chat.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anchoreer.chat.domain.entity.Chat;
import com.anchoreer.chat.domain.entity.ChatRoom;

public interface LoadChatPort {
    Page<Chat> loadAll(ChatRoom chatRoom, Pageable pageable);
}
