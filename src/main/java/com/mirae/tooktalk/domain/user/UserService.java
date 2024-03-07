package com.mirae.tooktalk.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(RegisterDTO registerDTO) {
        String username = registerDTO.getUsername();

        if (userRepository.findByUsername(username).isPresent()) {
            return;
        }

        userRepository.save(
                UserEntity.builder()
                        .username(registerDTO.getUsername())
                        .number(registerDTO.getNumber())
                        .password(bCryptPasswordEncoder.encode(registerDTO.getPassword()))
                        .age(registerDTO.getAge())
                        .gender(registerDTO.getGender())
                        .bio(registerDTO.getBio())
                        .build()
        );
    }
}
