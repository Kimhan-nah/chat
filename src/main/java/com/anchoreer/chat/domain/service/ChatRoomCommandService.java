package com.anchoreer.chat.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anchoreer.chat.application.port.in.ChatRoomCommandUseCase;
import com.anchoreer.chat.application.port.out.DeleteChatRoomPort;
import com.anchoreer.chat.application.port.out.DeleteChatRoomUserPort;
import com.anchoreer.chat.application.port.out.LoadChatRoomUserPort;
import com.anchoreer.chat.application.port.out.SaveChatRoomPort;
import com.anchoreer.chat.application.port.out.SaveChatRoomUserPort;
import com.anchoreer.chat.common.exception.custom.ConflictException;
import com.anchoreer.chat.common.exception.custom.NotFoundException;
import com.anchoreer.chat.domain.entity.ChatRoom;
import com.anchoreer.chat.domain.entity.ChatRoomUser;
import com.anchoreer.chat.domain.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomCommandService implements ChatRoomCommandUseCase {
    private final LoadChatRoomUserPort loadChatRoomUserPort;
    private final SaveChatRoomPort saveChatRoomPort;
    private final SaveChatRoomUserPort saveChatRoomUserPort;
    private final DeleteChatRoomPort deleteChatRoomPort;
    private final DeleteChatRoomUserPort deleteChatRoomUserPort;

    @Override
    @Transactional
    public ChatRoom createRoom(String title) {
        return saveChatRoomPort.save(ChatRoom.builder().title(title).build());
    }

    @Override
    @Transactional
    public void deleteRoom(ChatRoom chatRoom) {
        deleteChatRoomPort.delete(chatRoom);
    }

    @Override
    @Transactional
    public void joinRoom(User user, ChatRoom chatRoom) {
        loadChatRoomUserPort.load(user, chatRoom).ifPresent(chatRoomUser -> {
            throw new ConflictException("이미 채팅방에 참여 중입니다.");
        });

        ChatRoomUser chatRoomUser = ChatRoomUser.builder()
            .user(user)
            .chatRoom(chatRoom)
            .build();
        saveChatRoomUserPort.save(chatRoomUser);
    }

    @Override
    @Transactional
    public void leaveRoom(User user, ChatRoom chatRoom) {
        ChatRoomUser chatRoomUser = loadChatRoomUserPort.load(user, chatRoom)
            .orElseThrow(() -> new NotFoundException("사용자가 채팅방에 참여하고 있지 않습니다."));

        deleteChatRoomUserPort.delete(chatRoomUser);
    }
}
