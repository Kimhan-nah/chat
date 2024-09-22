package com.anchoreer.chat.application.port.out;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anchoreer.chat.domain.entity.ChatRoom;
import com.anchoreer.chat.domain.entity.ChatRoomDto;

public interface LoadChatRoomPort {
    Optional<ChatRoom> load(Long roomId);

    // 30분 내 접속자 수 기준으로 내림차순 정렬 + 최근 메세지 함께 조회
    Page<ChatRoomDto> loadAll(Pageable pageable);
}
