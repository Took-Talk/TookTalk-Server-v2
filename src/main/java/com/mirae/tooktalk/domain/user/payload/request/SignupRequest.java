package com.mirae.tooktalk.domain.user.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignupRequest {

    private String number;

    private String password;

    private String nickname;

    private String gender;

    private String age;

    private String mbti;

    private List<String> interests;

    private String bio;

    private String imgUrl;
}