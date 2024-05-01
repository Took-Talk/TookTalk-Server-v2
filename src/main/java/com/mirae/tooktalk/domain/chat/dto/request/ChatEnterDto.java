package com.mirae.tooktalk.domain.chat.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ChatEnterDto {
    private String roomId;
    private String nickname;
    private String mbti;
    private String bio;
    private List<String> interests;
    private String message;
    private String imgUrl;
}
