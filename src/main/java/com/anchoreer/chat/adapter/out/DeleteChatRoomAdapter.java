package com.anchoreer.chat.adapter.out;

import org.springframework.stereotype.Component;

import com.anchoreer.chat.application.port.out.DeleteChatRoomPort;
import com.anchoreer.chat.domain.entity.ChatRoom;
import com.anchoreer.chat.infrastructure.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteChatRoomAdapter implements DeleteChatRoomPort {
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public void delete(ChatRoom chatRoom) {
        chatRoom.delete();
        chatRoomRepository.save(chatRoom);
    }
}
