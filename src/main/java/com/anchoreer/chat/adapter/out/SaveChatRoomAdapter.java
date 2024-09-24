package com.anchoreer.chat.adapter.out;

import org.springframework.stereotype.Component;

import com.anchoreer.chat.application.port.out.SaveChatRoomPort;
import com.anchoreer.chat.domain.entity.ChatRoom;
import com.anchoreer.chat.infrastructure.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SaveChatRoomAdapter implements SaveChatRoomPort {
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }
}
