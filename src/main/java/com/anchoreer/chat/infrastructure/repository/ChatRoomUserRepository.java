package com.anchoreer.chat.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anchoreer.chat.domain.entity.ChatRoomUser;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {
}
