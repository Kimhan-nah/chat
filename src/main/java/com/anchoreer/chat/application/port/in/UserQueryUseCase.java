package com.anchoreer.chat.application.port.in;

import com.anchoreer.chat.domain.entity.User;

public interface UserQueryUseCase {
    User getUser(Long userId);
}
