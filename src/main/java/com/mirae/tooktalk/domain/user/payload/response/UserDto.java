package com.mirae.tooktalk.domain.user.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class UserDto {

    private int id;

    private String number;

    private String password;

    private String nickname;

    private String age;

    private String gender;

    private String mbti;

    private List<String> interests;

    private String imgUrl;

    private String bio;

    private LocalDateTime createdAt;

    private int status;

}
