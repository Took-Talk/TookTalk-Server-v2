package com.mirae.tooktalk.domain.chat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.mirae.tooktalk.domain.chat.ChatRoom;
import com.mirae.tooktalk.domain.chat.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
@Tag(name = "채팅", description = "채팅 관련 API")
public class ChatController {

    private final ChatService service;

    @Operation(summary = "채팅방 생성", description = "채팅 방을 생성합니다.")
    @PostMapping
    public ChatRoom createRoom(@RequestParam String name){
        return service.createRoom(name);
    }
}
