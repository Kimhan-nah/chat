package com.anchoreer.chat.adapter.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.anchoreer.chat.application.port.out.LoadChatPort;
import com.anchoreer.chat.domain.entity.Chat;
import com.anchoreer.chat.domain.entity.ChatRoom;
import com.anchoreer.chat.infrastructure.repository.ChatRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoadChatAdapter implements LoadChatPort {
    private final ChatRepository chatRepository;

    @Override
    public Page<Chat> loadAll(ChatRoom chatRoom, Pageable pageable) {
        return chatRepository.findAllByChatRoomId(chatRoom.getId(), pageable);
    }
}
