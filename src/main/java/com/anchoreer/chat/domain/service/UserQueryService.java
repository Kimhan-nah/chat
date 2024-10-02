package com.anchoreer.chat.domain.service;

import org.springframework.stereotype.Service;

import com.anchoreer.chat.application.port.in.UserQueryUseCase;
import com.anchoreer.chat.application.port.out.LoadUserPort;
import com.anchoreer.chat.common.exception.custom.NotFoundException;
import com.anchoreer.chat.domain.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserQueryService implements UserQueryUseCase {
    private final LoadUserPort loadUserPort;

    @Override
    public User getUser(Long userId) {
        return loadUserPort.load(userId)
            .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));
    }
}
