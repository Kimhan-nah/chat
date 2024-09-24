package com.anchoreer.chat.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anchoreer.chat.domain.entity.Chat;

public interface ChatRepositoryCustom {
    // TODO cursor 기반 페이징으로 변경
    Page<Chat> findAllByChatRoomId(long chatRoomId, Pageable pageable);
}
