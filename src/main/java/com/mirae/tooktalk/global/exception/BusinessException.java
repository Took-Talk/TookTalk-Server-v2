package com.mirae.tooktalk.global.exception;

import com.mirae.tooktalk.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException{
    private final ErrorProperty error;
}