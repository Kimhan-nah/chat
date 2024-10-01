package com.anchoreer.chat.domain.entity;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomDto {
    private Long id;
    private String title;
    private Long activeUsersCount;
    private String lastMessage;
    private LocalDateTime lastMessageTime;

    @Builder
    @QueryProjection
    public ChatRoomDto(Long id, String title, Long activeUsersCount, String lastMessage,
        LocalDateTime lastMessageTime) {
        this.id = id;
        this.title = title;
        this.activeUsersCount = activeUsersCount;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }

    public static ChatRoomDto from(ChatRoom chatRoom) {
        return ChatRoomDto.builder()
            .id(chatRoom.getId())
            .title(chatRoom.getTitle())
            .activeUsersCount(0L)
            .lastMessage(null)
            .lastMessageTime(null)
            .build();
    }
}
