package com.anchoreer.chat.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anchoreer.chat.domain.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
