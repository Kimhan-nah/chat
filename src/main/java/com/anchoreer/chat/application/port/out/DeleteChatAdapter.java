package com.anchoreer.chat.application.port.out;

import org.springframework.stereotype.Component;

import com.anchoreer.chat.domain.entity.Chat;
import com.anchoreer.chat.infrastructure.repository.ChatRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteChatAdapter implements DeleteChatPort {
    private final ChatRepository chatRepository;

    @Override
    public void delete(Chat chat) {
        chat.delete();
        chatRepository.save(chat);
    }
}
