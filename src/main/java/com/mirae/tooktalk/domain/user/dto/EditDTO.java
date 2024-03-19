package com.mirae.tooktalk.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EditDTO {

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
        관심사
    */
    private List<String> interests;

}