package com.anchoreer.chat.adapter.out;

import org.springframework.stereotype.Component;

import com.anchoreer.chat.application.port.out.DeleteChatRoomUserPort;
import com.anchoreer.chat.domain.entity.ChatRoomUser;
import com.anchoreer.chat.infrastructure.repository.ChatRoomUserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteChatRoomUserAdapter implements DeleteChatRoomUserPort {
    private final ChatRoomUserRepository chatRoomUserRepository;

    @Override
    public void delete(ChatRoomUser chatRoomUser) {
        chatRoomUser.delete();
        chatRoomUserRepository.save(chatRoomUser);
    }
}
