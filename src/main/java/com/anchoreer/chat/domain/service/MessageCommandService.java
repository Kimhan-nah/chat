package com.anchoreer.chat.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anchoreer.chat.application.port.in.MessageCommandUseCase;
import com.anchoreer.chat.application.port.out.DeleteChatPort;
import com.anchoreer.chat.application.port.out.LoadChatRoomUserPort;
import com.anchoreer.chat.application.port.out.SaveChatPort;
import com.anchoreer.chat.common.exception.custom.ForbiddenException;
import com.anchoreer.chat.common.exception.custom.NotFoundException;
import com.anchoreer.chat.domain.entity.Chat;
import com.anchoreer.chat.domain.entity.ChatRoom;
import com.anchoreer.chat.domain.entity.ChatRoomUser;
import com.anchoreer.chat.domain.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageCommandService implements MessageCommandUseCase {
    private final LoadChatRoomUserPort loadChatRoomUserPort;
    private final SaveChatPort saveChatPort;
    private final DeleteChatPort deleteChatPort;

    @Override
    @Transactional
    public Chat sendMessage(ChatRoom chatRoom, User sender, String message) {
        ChatRoomUser chatRoomUser = loadChatRoomUserPort.load(sender, chatRoom)
            .orElseThrow(() -> new NotFoundException("채팅방에 속해있지 않습니다."));

        Chat chat = Chat.builder()
            .chatRoomUser(chatRoomUser)
            .contents(message)
            .build();
        return saveChatPort.save(chat);
    }

    @Override
    @Transactional
    public void deleteMessage(ChatRoom chatRoom, User sender, Chat chat) {
        ChatRoomUser chatRoomUser = loadChatRoomUserPort.load(sender, chatRoom)
            .orElseThrow(() -> new ForbiddenException("채팅방에 속해있지 않습니다."));
        if (!chat.getChatRoomUser().equals(chatRoomUser)) {
            throw new ForbiddenException("메시지를 삭제할 수 없습니다.");
        }

        deleteChatPort.delete(chat);
    }
}
