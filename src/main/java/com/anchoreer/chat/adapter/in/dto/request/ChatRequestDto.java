package com.anchoreer.chat.adapter.in.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRequestDto {
    @NotNull(message = "senderId가 비어있습니다.")
    private Long senderId;

    @Size(max = 5000, message = "message는 5000자 이하로 입력해주세요.")
    private String message;
}
