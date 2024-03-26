package com.mirae.tooktalk.domain.user.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String number;

    private String password;

}