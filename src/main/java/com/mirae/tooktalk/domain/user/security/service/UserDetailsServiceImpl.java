package com.mirae.tooktalk.domain.user.security.service;

import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
import com.mirae.tooktalk.domain.user.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String number) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByNumber(number)
                .orElseThrow(() -> new UsernameNotFoundException("유저의 전화번호를 찾을 수 없습니다: " + number));
        return UserDetailsImpl.build(userEntity);
    }
}