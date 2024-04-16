package com.mirae.tooktalk.domain.chat.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class MatchingUserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @Column
    private String mbti;

    @Column
    private int userId;

    @Builder
    public MatchingUserEntity(String mbti, int userId) {
        this.mbti = mbti;
        this.userId = userId;
    }
}
