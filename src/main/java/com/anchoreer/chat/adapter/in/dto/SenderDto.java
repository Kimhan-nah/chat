package com.anchoreer.chat.adapter.in.dto;

import com.anchoreer.chat.domain.entity.User;

import lombok.Getter;

@Getter
public class SenderDto {
    private final Long id;
    private final String name;

    public SenderDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SenderDto from(User user) {
        return new SenderDto(user.getId(), user.getName());
    }
}
