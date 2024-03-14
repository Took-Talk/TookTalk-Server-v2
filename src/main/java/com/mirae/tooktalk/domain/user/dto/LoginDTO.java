package com.mirae.tooktalk.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    /*
        전화번호
    */
    private String number;
    /*
        비밀번호
    */
    private String password;
}
