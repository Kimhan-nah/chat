package com.anchoreer.chat.adapter.out;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.anchoreer.chat.application.port.out.LoadChatRoomPort;
import com.anchoreer.chat.domain.entity.ChatRoom;
import com.anchoreer.chat.domain.entity.ChatRoomDto;
import com.anchoreer.chat.infrastructure.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoadChatRoomAdapter implements LoadChatRoomPort {
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public Optional<ChatRoom> load(Long roomId) {
        return chatRoomRepository.findById(roomId);
    }

    @Override
    public Page<ChatRoomDto> loadAll(Pageable pageable) {
        return chatRoomRepository.findAllChatRoomDto(pageable);
    }
}
