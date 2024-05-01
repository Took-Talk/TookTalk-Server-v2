package com.mirae.tooktalk.domain.image.dto;

import lombok.Builder;

@Builder
public record ImageDTO(
        String imgUrl
) {
}