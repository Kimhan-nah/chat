package com.anchoreer.chat.adapter.out;

import org.springframework.stereotype.Component;

import com.anchoreer.chat.application.port.out.SaveChatPort;
import com.anchoreer.chat.domain.entity.Chat;
import com.anchoreer.chat.infrastructure.repository.ChatRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SaveChatAdapter implements SaveChatPort {
    private final ChatRepository chatRepository;

    @Override
    public Chat save(Chat chat) {
        return chatRepository.save(chat);
    }
}
