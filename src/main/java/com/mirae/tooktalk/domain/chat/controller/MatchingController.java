package com.mirae.tooktalk.domain.chat.controller;

import com.mirae.tooktalk.domain.chat.service.MatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "매칭", description = "매칭 관련 API")
public class MatchingController {
    private final MatchingService matchingService;

    @Operation(summary = "mbti 매칭", description = "mbti로 유저를 매칭합니다.")
    @PostMapping("/matching/{mbti}")
    public ResponseEntity matching(
            Authentication authentication,
            @PathVariable String mbti
    ){
        return ResponseEntity.ok().body(matchingService.matching(authentication, mbti));
    }
}
