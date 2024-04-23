package com.mirae.tooktalk.domain.chat.service;

import com.mirae.tooktalk.domain.chat.dto.response.MatchingResponse;
import com.mirae.tooktalk.domain.chat.entity.MatchingUserEntity;
import com.mirae.tooktalk.domain.chat.repository.MatchingRepository;
import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
import com.mirae.tooktalk.domain.user.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MatchingService {

    private final UserRepository userRepository;
    private final MatchingRepository matchingRepository;

    public MatchingResponse matching(Authentication authentication, String mbti){
        UserEntity user = UserEntity.findUserByNickname(userRepository, authentication.getName());
        hideUserPassword(user);
        MatchingUserEntity matchingUserEntity = findMatchingUserByMbti(mbti);

        return handleMatchingResult(user, matchingUserEntity, mbti);
    }

    /* 유저 정보 반환 시 패스워드 공백 처리 */
    private void hideUserPassword(UserEntity user) {
        user.hidePassword("");
    }

    /* mbti로 유저 매칭 */
    private MatchingUserEntity findMatchingUserByMbti(String mbti) {
        return matchingRepository.findByMbti(mbti);
    }

    /* 매칭 결과 처리 */
    private MatchingResponse handleMatchingResult(UserEntity user, MatchingUserEntity matchingUserEntity, String mbti) {
        if (matchingUserEntity == null) {
            return addWaitingList(user, mbti);
        }
        return removeFromWaitingList(user, matchingUserEntity);
    }

    /* 해당하는 유저가 없을 경우 대기열에 추가하는 메서드 */
    private MatchingResponse addWaitingList(UserEntity user, String mbti) {
        int roomId = matchingRepository.save(
                MatchingUserEntity.builder()
                        .userId(user.getId())
                        .mbti(mbti)
                        .build()
        ).getRoomId();

        return MatchingResponse.builder()
                .roomId(roomId)
                .userInfo(user)
                .matching(false)
                .build();
    }

    /* 매칭 시 본인 데이터를 대기열에서 지우는 메서드 */
    private MatchingResponse removeFromWaitingList(UserEntity user, MatchingUserEntity matchingUserEntity) {
        matchingRepository.delete(matchingUserEntity);

        return MatchingResponse.builder()
                .roomId(matchingUserEntity.getRoomId())
                .userInfo(user)
                .matching(true)
                .build();
    }
}
