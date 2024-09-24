package com.anchoreer.chat.adapter.out;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.anchoreer.chat.application.port.out.LoadChatRoomUserPort;
import com.anchoreer.chat.domain.entity.ChatRoom;
import com.anchoreer.chat.domain.entity.ChatRoomUser;
import com.anchoreer.chat.domain.entity.User;
import com.anchoreer.chat.infrastructure.repository.ChatRoomUserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoadChatRoomUserAdapter implements LoadChatRoomUserPort {
    private final ChatRoomUserRepository chatRoomUserRepository;

    @Override
    public Optional<ChatRoomUser> load(User user, ChatRoom chatRoom) {
        return chatRoomUserRepository.findByUserAndChatRoom(user, chatRoom);
    }
}
