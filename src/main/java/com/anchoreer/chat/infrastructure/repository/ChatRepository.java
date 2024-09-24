package com.anchoreer.chat.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.anchoreer.chat.domain.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Page<Chat> findAllByChatRoom_Id(long chatRoomId, Pageable pageable);
}
