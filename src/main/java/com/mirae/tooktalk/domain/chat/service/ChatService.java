package com.mirae.tooktalk.domain.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import com.mirae.tooktalk.domain.chat.ChatRoom;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@Data
@Service
public class ChatService {
    private final ObjectMapper mapper;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }

    public ChatRoom createRoom(String name){
        String roomId = UUID.randomUUID().toString();

        /* Builder를 사용하여 ChatRoom 을 Build */
        ChatRoom room = ChatRoom.builder()
                .roomId(roomId)
                .name(name)
                .build();
        chatRooms.put(roomId,room); /* 랜덤 아이디와 room 정보를 Map 에 저장 */

        return room;
    }

    public <T> void sendMessage(WebSocketSession session, T message){
        try{
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));;
        }catch (IOException e){
            log.error(e.getMessage(),e);
        }
    }
}
