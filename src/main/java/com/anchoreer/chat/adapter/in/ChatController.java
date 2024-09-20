package com.anchoreer.chat.adapter.in;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anchoreer.chat.adapter.in.dto.ChatMessage;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ChatController {

    @MessageMapping("/chat/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        System.out.println("sendMessage: " + chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat/addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage) {
        // 사용자가 채팅방에 입장했을 때의 로직
        System.out.println("addUser: " + chatMessage);
        return chatMessage;
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
}

