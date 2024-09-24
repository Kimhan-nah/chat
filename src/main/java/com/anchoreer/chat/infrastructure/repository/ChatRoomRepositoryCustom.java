package com.anchoreer.chat.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anchoreer.chat.domain.entity.ChatRoomDto;

public interface ChatRoomRepositoryCustom {
    Page<ChatRoomDto> findAllChatRoomDto(Pageable pageable);
}
