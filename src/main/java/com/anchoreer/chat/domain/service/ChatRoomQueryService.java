package com.anchoreer.chat.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anchoreer.chat.application.port.in.ChatRoomQueryUseCase;
import com.anchoreer.chat.application.port.out.LoadChatPort;
import com.anchoreer.chat.application.port.out.LoadChatRoomPort;
import com.anchoreer.chat.common.exception.custom.NotFoundException;
import com.anchoreer.chat.domain.entity.Chat;
import com.anchoreer.chat.domain.entity.ChatRoom;
import com.anchoreer.chat.domain.entity.ChatRoomDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomQueryService implements ChatRoomQueryUseCase {
    private final LoadChatRoomPort loadChatRoomPort;
    private final LoadChatPort loadChatPort;

    @Override
    @Transactional(readOnly = true)
    public Page<Chat> getChatsByRoomId(Long roomId, Pageable pageable) {
        ChatRoom chatRoom = getChatRoom(roomId);
        return loadChatPort.loadAll(chatRoom, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChatRoomDto> getChatRooms(Pageable pageable) {
        return loadChatRoomPort.loadAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ChatRoom getChatRoom(Long roomId) {
        return loadChatRoomPort.load(roomId)
            .orElseThrow(() -> new NotFoundException("채팅방이 존재하지 않습니다."));
    }
}
