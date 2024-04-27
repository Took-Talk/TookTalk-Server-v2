package com.mirae.tooktalk.domain.user.exception;

import com.mirae.tooktalk.domain.user.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException{
    private final ErrorProperty error;
}