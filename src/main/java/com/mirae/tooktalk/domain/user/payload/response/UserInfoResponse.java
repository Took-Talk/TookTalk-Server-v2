package com.mirae.tooktalk.domain.user.payload.response;

import lombok.Builder;

import java.util.List;

@Builder
public class UserInfoResponse {
    private int id;

    private String number;

    private String nickname;

    private String age;

    private String gender;

    private String mbti;

    private List<String> interests;

    private String bio;
}
