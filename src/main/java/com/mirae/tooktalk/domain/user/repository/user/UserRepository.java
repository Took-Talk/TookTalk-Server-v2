package com.mirae.tooktalk.domain.user.repository.user;

import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
import com.mirae.tooktalk.domain.user.payload.request.UserInfoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByNumber(String number);
    Optional<UserInfoRequest> findByNickname(String nickname);
    Boolean existsByNumber(String number);

    Boolean existsByNickname(String nickname);

    Optional<UserEntity> findByNicknameEquals(String nickname);
}