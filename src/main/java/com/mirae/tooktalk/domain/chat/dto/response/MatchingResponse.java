package com.mirae.tooktalk.domain.chat.dto.response;

import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
import lombok.Builder;

@Builder
public record MatchingResponse (
        int roomId,

        UserEntity userInfo,

        boolean matching
){}
