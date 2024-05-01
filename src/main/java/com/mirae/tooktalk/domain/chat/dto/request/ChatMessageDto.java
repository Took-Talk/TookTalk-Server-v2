package com.mirae.tooktalk.domain.chat.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {
    private String roomId;
    private String nickname;
    private String message;
    private String imgUrl;
}
