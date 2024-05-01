package com.mirae.tooktalk.domain.user.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoRequest {
    private String nickname;
    private String mbti;
    private String bio;
    private String imgUrl;
}
