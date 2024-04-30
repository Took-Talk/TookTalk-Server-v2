package com.mirae.tooktalk.global.common.S3.dto;

import lombok.Builder;

@Builder
public record ImageDTO(
        String imgUrl
) {
}