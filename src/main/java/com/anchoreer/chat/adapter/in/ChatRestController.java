package com.anchoreer.chat.adapter.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anchoreer.chat.adapter.in.dto.response.ChatRoomsResponseDto;
import com.anchoreer.chat.adapter.in.dto.response.ChatsResponseDto;
import com.anchoreer.chat.application.port.in.ChatRoomCommandUseCase;
import com.anchoreer.chat.application.port.in.ChatRoomQueryUseCase;
import com.anchoreer.chat.domain.entity.Chat;
import com.anchoreer.chat.domain.entity.ChatRoomDto;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatrooms")
public class ChatRestController {
    private final ChatRoomCommandUseCase chatRoomCommandUseCase;
    private final ChatRoomQueryUseCase chatRoomQueryUseCase;

    @PostMapping
    public ResponseEntity<Void> createRoom(String title) {
        chatRoomCommandUseCase.createRoom(title);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<ChatRoomsResponseDto> getRooms(@PageableDefault Pageable pageable) {
        Page<ChatRoomDto> chatRooms = chatRoomQueryUseCase.getChatRooms(pageable);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ChatRoomsResponseDto.of(chatRooms.getContent(), chatRooms.getTotalPages()));
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<ChatsResponseDto> getMessages(@PathVariable @Positive Long roomId,
        @PageableDefault Pageable pageable) {
        Page<Chat> chatsByRoomId = chatRoomQueryUseCase.getChatsByRoomId(roomId, pageable);
        chatsByRoomId.forEach(this::updateDeletedMessage);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ChatsResponseDto.from(chatsByRoomId.getContent(), chatsByRoomId.getTotalPages()));
    }

    /**
     * isDeleted가 true인 경우 "삭제된 메시지입니다."로 대체
     * @param chat
     */
    private void updateDeletedMessage(Chat chat) {
        if (Boolean.TRUE.equals(chat.getIsDeleted())) {
            chat.updateContents("삭제된 메시지입니다.");
        }
    }
}

