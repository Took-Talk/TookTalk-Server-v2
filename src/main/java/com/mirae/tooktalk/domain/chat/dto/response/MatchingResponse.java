package com.mirae.tooktalk.domain.chat.dto.response;

import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
import lombok.Builder;

@Builder
public class MatchingResponse {

    private int roomId;

    private UserEntity userInfo;

    private boolean matching;



}
