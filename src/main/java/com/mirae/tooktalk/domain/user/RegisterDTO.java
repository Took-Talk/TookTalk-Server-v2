package com.mirae.tooktalk.domain.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {
    /*
        전화번호
    */
    private String number;

    /*
        닉네임
    */
    private String username;

    /*
        비밀번호
    */
    private String password;

    /*
        나이
    */
    private String age;

    /*
        성별
    */
    private String gender;

    /*
        자기 소개
    */
    private String bio;
}
