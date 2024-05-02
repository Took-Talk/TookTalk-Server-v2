package com.mirae.tooktalk.domain.chat.dto.response;

import com.mirae.tooktalk.domain.user.payload.response.UserDto;
import lombok.Builder;

@Builder
public record MatchingResponse (
        int roomId,

        UserDto userInfo,

        boolean matching
){}
