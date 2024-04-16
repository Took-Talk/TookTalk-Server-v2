package com.mirae.tooktalk.domain.chat.controller;

import com.mirae.tooktalk.domain.chat.service.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MatchingController {
    private final MatchingService matchingService;

    @PostMapping("/matching/{mbti}")
    public Map<String, Integer> matching(
            Authentication authentication,
            @PathVariable String mbti
    ){
        return matchingService.matching(authentication, mbti);
    }
}
