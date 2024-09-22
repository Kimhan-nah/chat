package com.anchoreer.chat.application.port.in;

public interface MessageSendingUseCase {
    void sendMessage(Long roomId, String message);
}
