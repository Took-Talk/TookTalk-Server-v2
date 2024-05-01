package com.mirae.tooktalk.domain.chat.controller;

import com.mirae.tooktalk.domain.chat.dto.request.ChatEnterDto;
import com.mirae.tooktalk.domain.chat.dto.request.ChatMessageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Tag(name = "채팅", description = "채팅 관련 API")
public class ChatController {

    private final SimpMessagingTemplate template;

    @Operation(summary = "채팅방 입장", description = "채팅 방에 입장합니다.")
    @MessageMapping("/chat/enter")
    public void enterChatRoom(ChatEnterDto message) {
        message.setMessage(message.getNickname() + "님이 입장하셨습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @Operation(summary = "채팅 메세지", description = "메세지를 전송합니다.")
    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
