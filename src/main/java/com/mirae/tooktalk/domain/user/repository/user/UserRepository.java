package com.mirae.tooktalk.domain.user.repository.user;

import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByNumber(String number);
    Boolean existsByNumber(String number);
}