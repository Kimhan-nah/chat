package com.anchoreer.chat.adapter.in.dto.response;

import java.util.List;

import com.anchoreer.chat.domain.entity.ChatRoomDto;

import lombok.Getter;

@Getter
public class ChatRoomsResponseDto {
    private final List<ChatRoomDto> chatRooms;
    private final int totalPage;

    public ChatRoomsResponseDto(List<ChatRoomDto> chatRooms, int totalPage) {
        this.chatRooms = chatRooms;
        this.totalPage = totalPage;
    }

    public static ChatRoomsResponseDto of(List<ChatRoomDto> chatRooms, int totalPage) {
        return new ChatRoomsResponseDto(chatRooms, totalPage);
    }
}
