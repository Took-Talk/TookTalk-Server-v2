package com.mirae.tooktalk.domain.chat.service;

import com.mirae.tooktalk.domain.chat.entity.MatchingUserEntity;
import com.mirae.tooktalk.domain.chat.repository.MatchingRepository;
import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
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

    public Map<String, Object> matching(Authentication authentication, String mbti){
        UserEntity user = userRepository.findByNicknameEquals(authentication.getName()).get();
        user.hidePassword("");
        Map<String, Object> map = new HashMap<>();
        MatchingUserEntity matchingUserEntity = matchingRepository.findByMbti(mbti);
        if (matchingUserEntity == null){
            map = addWaitingList(user, mbti);
        } else{
            map = removeFromWaitingList(user, matchingUserEntity);
        }
        return map;
    }

    /* 해당하는 유저가 없을 경우 대기열에 추가하는 메서드 */
    private Map<String, Object> addWaitingList(UserEntity user, String mbti) {
        int roomId = matchingRepository.save(
                MatchingUserEntity.builder()
                        .userId(user.getId())
                        .mbti(mbti)
                        .build()
        ).getRoomId();

        Map<String, Object> map = new HashMap<>();
        map.put("roomId", roomId);
        map.put("userInfo", user);
        map.put("matching", false);
        return map;
    }

    /* 매칭 시 본인 데이터를 대기열에서 지우는 메서드 */
    private Map<String, Object> removeFromWaitingList(UserEntity user, MatchingUserEntity matchingUserEntity) {
        matchingRepository.delete(matchingUserEntity);

        Map<String, Object> map = new HashMap<>();
        map.put("roomId", matchingUserEntity.getRoomId());
        map.put("userInfo", user);
        map.put("matching", true);
        return map;
    }
}
