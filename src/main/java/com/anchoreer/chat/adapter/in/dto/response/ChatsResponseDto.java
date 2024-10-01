package com.anchoreer.chat.adapter.in.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.anchoreer.chat.adapter.in.dto.ChatDto;
import com.anchoreer.chat.domain.entity.Chat;

import lombok.Getter;

@Getter
public class ChatsResponseDto {
    private final List<ChatDto> chats;
    private final int totalPage;

    public ChatsResponseDto(List<ChatDto> chats, int totalPage) {
        this.chats = chats;
        this.totalPage = totalPage;
    }

    public static ChatsResponseDto from(List<Chat> chats, int totalPage) {
        return new ChatsResponseDto(chats.stream()
            .map(ChatDto::from)
            .collect(Collectors.toList()), totalPage);
    }
}
