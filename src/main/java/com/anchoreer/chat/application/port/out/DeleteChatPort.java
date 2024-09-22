package com.anchoreer.chat.application.port.out;

import com.anchoreer.chat.domain.entity.Chat;

public interface DeleteChatPort {
    void delete(Chat chat);
}
