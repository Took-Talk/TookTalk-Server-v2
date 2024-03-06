package com.mirae.tooktalk.domain.chat.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class ChatDTO {
    // 메시지  타입 : 입장, 채팅, 퇴장
    public enum MessageType{
        ENTER, TALK, GONE
    }

    private MessageType type;
    private String message;

    private String roomId;
    private String sender;
    private String time;
}