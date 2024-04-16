package com.mirae.tooktalk.domain.chat.service;

import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
import com.mirae.tooktalk.domain.user.payload.request.MatchingRequest;
import com.mirae.tooktalk.domain.user.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final UserRepository userRepository;
    private final SimpMessagingTemplate template;

    /** 유저 매칭 알고리즘 **/
    public String matchAndEnterChatRoom(MatchingRequest matchingRequest, Authentication authentication) {
        String userId = authentication.getName();

        // 유저가 원하는 조건에 맞는 다른 유저를 찾아서 매칭
        List<UserEntity> matchedUsers = userRepository.findByMbti(matchingRequest.getMbti());

        // 자신을 제외한 매칭된 유저 목록에서 랜덤으로 선택
        matchedUsers.removeIf(user -> user.getNumber() == userId);
        if (!matchedUsers.isEmpty()) {
            UserEntity matchedUser = getRandomUser(matchedUsers);
            String roomId = createChatRoom(userId, matchedUser.getNumber());
            return roomId;
        } else {
            return null; // 매칭된 유저가 없을 경우
        }
    }

    private UserEntity getRandomUser(List<UserEntity> userList) {
        Random random = new Random();
        int randomIndex = random.nextInt(userList.size());
        return userList.get(randomIndex);
    }

    private String createChatRoom(String userId1, String userId2) {
        String roomId = UUID.randomUUID().toString();
        // 채팅방 생성 로직
        // 예를 들어, 채팅방 정보를 데이터베이스에 저장하고 채팅방의 ID를 반환하는 로직을 구현
        return roomId;
    }
}
