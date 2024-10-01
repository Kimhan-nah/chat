package com.anchoreer.chat.adapter.in.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomRequestDto {
    @Size(max = 255, message = "title은 255자 이하로 입력해주세요.")
    private String title;
}
