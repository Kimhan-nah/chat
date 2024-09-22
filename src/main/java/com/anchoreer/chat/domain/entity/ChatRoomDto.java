package com.anchoreer.chat.domain.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomDto {
    private Long id;
    private String title;
    private int activeUsersCount;
    private String lastMessage;
    private LocalDateTime lastMessageTime;

    @Builder
    public ChatRoomDto(Long id, String title, int activeUsersCount, String lastMessage, LocalDateTime lastMessageTime) {
        this.id = id;
        this.title = title;
        this.activeUsersCount = activeUsersCount;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }
}
