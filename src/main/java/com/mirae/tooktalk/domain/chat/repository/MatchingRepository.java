package com.mirae.tooktalk.domain.chat.repository;

import com.mirae.tooktalk.domain.chat.entity.MatchingUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingRepository extends JpaRepository<MatchingUserEntity, Long> {
    MatchingUserEntity findByMbti(String mbti);

}
