package com.mirae.tooktalk.domain.user.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchingRequest {
    private String mbti;
    private String age;
    private String gender;
}
