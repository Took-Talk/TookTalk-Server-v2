package com.mirae.tooktalk.domain.user.exception;

import com.mirae.tooktalk.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserIdExistsException extends CustomException {
    public static final CustomException EXCEPTION = new UserIdExistsException();

    private UserIdExistsException() {
        super(HttpStatus.BAD_REQUEST, "해당 아이디는 이미 존재합니다.");
    }
}
