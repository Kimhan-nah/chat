package com.anchoreer.chat.adapter.out;

import org.springframework.stereotype.Component;

import com.anchoreer.chat.application.port.out.SaveChatRoomUserPort;
import com.anchoreer.chat.domain.entity.ChatRoomUser;
import com.anchoreer.chat.infrastructure.repository.ChatRoomUserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SaveChatRoomUserAdapter implements SaveChatRoomUserPort {
    private final ChatRoomUserRepository chatRoomUserRepository;

    @Override
    public ChatRoomUser save(ChatRoomUser chatRoomUser) {
        return chatRoomUserRepository.save(chatRoomUser);
    }
}
