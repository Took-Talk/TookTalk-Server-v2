package com.mirae.tooktalk.domain.chat;

import lombok.Builder;
import lombok.Data;
import com.mirae.tooktalk.domain.chat.service.ChatService;
import com.mirae.tooktalk.domain.chat.dto.ChatDTO;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
public class ChatRoom {

    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId,String name){
        this.roomId = roomId;
        this.name = name;
    }
    public void handleAction(WebSocketSession session, ChatDTO message, ChatService service) {
        if (message.getType().equals(ChatDTO.MessageType.ENTER)) {
            sessions.add(session);
            message.setMessage(message.getSender() + " 님이 입장하였습니다.");
            sendMessage(message, service);
        } else if (message.getType().equals(ChatDTO.MessageType.TALK)) {
            message.setMessage(message.getMessage());
            sendMessage(message, service);
        } else if (message.getType().equals(ChatDTO.MessageType.GONE)) {
            sessions.remove(session);
            message.setMessage(message.getSender() + " 님이 퇴장하였습니다.");
            sendMessage(message, service);
        }
    }
    public <T> void sendMessage(T message, ChatService service){
        sessions.parallelStream().forEach(sessions -> service.sendMessage(sessions,message));
    }
}
