package com.anchoreer.chat.application.port.out;

import java.util.Optional;

import com.anchoreer.chat.domain.entity.User;

public interface LoadUserPort {
    Optional<User> load(Long userId);
}
