package com.anchoreer.chat.application.port.out;

import com.anchoreer.chat.domain.entity.Chat;

public interface SaveChatPort {
    Chat save(Chat chat);
}
