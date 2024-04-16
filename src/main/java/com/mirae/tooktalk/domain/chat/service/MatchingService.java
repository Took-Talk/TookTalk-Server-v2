package com.mirae.tooktalk.domain.chat.service;

import com.mirae.tooktalk.domain.chat.entity.MatchingUserEntity;
import com.mirae.tooktalk.domain.chat.repository.MatchingRepository;
import com.mirae.tooktalk.domain.user.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MatchingService {

    private final UserRepository userRepository;
    private final MatchingRepository matchingRepository;

    public Map<String, Integer> matching(Authentication authentication, String mbti){
        int userId = userRepository.findByNicknameEquals(authentication.getName()).get().getId();
        Map<String, Integer> map = new HashMap();
        MatchingUserEntity matchingUserEntity = matchingRepository.findByMbti(mbti);
        if (matchingUserEntity == null){
            int roomId = matchingRepository.save(
                    MatchingUserEntity.builder()
                            .userId(userId)
                            .mbti(mbti)
                            .build()
            ).getRoomId();

            map.put("roomId" , roomId);
            return map;
        } else{
            matchingRepository.delete(matchingUserEntity);
            map.put("roomId" , matchingUserEntity.getRoomId());
            return map;
        }

    }
}
