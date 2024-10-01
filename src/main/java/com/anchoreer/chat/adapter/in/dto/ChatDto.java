package com.anchoreer.chat.adapter.in.dto;

import java.time.LocalDateTime;

import com.anchoreer.chat.domain.entity.Chat;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatDto {
    private final Long id;
    private final SenderDto sender;
    private final String contents;
    private final Boolean isDeleted;
    private final LocalDateTime createdAt;

    @Builder
    public ChatDto(Long id, SenderDto sender, String contents, Boolean isDeleted, LocalDateTime createdAt) {
        this.id = id;
        this.sender = sender;
        this.contents = contents;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
    }

    // Chat -> ChatDto
    public static ChatDto from(Chat chat) {
        return ChatDto.builder()
            .id(chat.getId())
            .sender(SenderDto.from(chat.getChatRoomUser().getUser()))
            .contents(chat.getContents())
            .isDeleted(chat.getIsDeleted())
            .createdAt(chat.getCreatedAt())
            .build();
    }
}
