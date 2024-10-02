package com.anchoreer.chat.adapter.in;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.anchoreer.chat.adapter.in.dto.ChatDto;
import com.anchoreer.chat.adapter.in.dto.SenderDto;
import com.anchoreer.chat.adapter.in.dto.request.ChatRequestDto;
import com.anchoreer.chat.application.port.in.ChatRoomCommandUseCase;
import com.anchoreer.chat.application.port.in.ChatRoomQueryUseCase;
import com.anchoreer.chat.application.port.in.MessageCommandUseCase;
import com.anchoreer.chat.application.port.in.UserQueryUseCase;
import com.anchoreer.chat.domain.entity.Chat;
import com.anchoreer.chat.domain.entity.ChatRoom;
import com.anchoreer.chat.domain.entity.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ChatWebSocketController {
    private final MessageCommandUseCase messageCommandUseCase;
    private final ChatRoomQueryUseCase chatRoomQueryUseCase;
    private final ChatRoomCommandUseCase chatRoomCommandUseCase;
    private final UserQueryUseCase userQueryUseCase;

    @MessageMapping("/rooms/{roomId}")
    @SendTo("/rooms/{roomId}")
    public ChatDto chat(@DestinationVariable Long roomId, @Valid ChatRequestDto chatRequestDto) {
        ChatRoom chatRoom = chatRoomQueryUseCase.getChatRoom(roomId);
        User user = userQueryUseCase.getUser(chatRequestDto.getSenderId());
        Chat chat = messageCommandUseCase.sendMessage(chatRoom, user, chatRequestDto.getMessage());
        return ChatDto.from(chat);
    }

    @MessageMapping("/chat/{roomId}/join")
    @SendTo("/rooms/{roomId}")
    public void join(@DestinationVariable Long roomId, SenderDto senderDto) {
        // 채팅방 참여 로직
        User user = userQueryUseCase.getUser(senderDto.getId());
        ChatRoom chatRoom = chatRoomQueryUseCase.getChatRoom(roomId);
        chatRoomCommandUseCase.joinRoom(user, chatRoom);

        // TODO 접속자 수 등 추가 정보를 포함하여 반환
        return;
    }
}
