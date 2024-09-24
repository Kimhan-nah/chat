package com.anchoreer.chat.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anchoreer.chat.domain.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomRepositoryCustom {
}
