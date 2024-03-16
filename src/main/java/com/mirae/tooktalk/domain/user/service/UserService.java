package com.mirae.tooktalk.domain.user.service;

import com.mirae.tooktalk.domain.user.dto.EditDTO;
import com.mirae.tooktalk.domain.user.dto.RegisterDTO;
import com.mirae.tooktalk.domain.user.dto.UserDTO;
import com.mirae.tooktalk.domain.user.entity.UserEntity;
import com.mirae.tooktalk.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입 메서드
    public void register(RegisterDTO registerDTO) {
        String number = registerDTO.getNumber();

        // 사용자 존재 여부 확인
        if (userRepository.findByNumber(number).isPresent()) {
            return; // 이미 존재하는 사용자면 메서드 종료
        }

        // 존재하지 않는 사용자라면 새로운 사용자 정보를 저장
        userRepository.save(
                UserEntity.builder()
                        .username(registerDTO.getUsername())
                        .password(bCryptPasswordEncoder.encode(registerDTO.getPassword()))
                        .age(registerDTO.getAge())
                        .gender(registerDTO.getGender())
                        .interests(registerDTO.getInterests())
                        .bio(registerDTO.getBio())
                        .number(registerDTO.getNumber())
                        .role("ROLE_USER")
                        .build()
        );
    }

    // 사용자 정보를 수정하는 메서드
    public void editUser(EditDTO editDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // 현재 사용자의 인증 정보를 가져옴

        String username = auth.getName();

        // 현재 인증된 사용자를 데이터베이스에서 조회하여 가져옴
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User Entity Not Found"));

        if (editDTO.getUsername() != null) {
            user.setUsername(editDTO.getUsername());
        }
        if (editDTO.getAge() != null) {
            user.setAge(editDTO.getAge());
        }
        if (editDTO.getGender() != null) {
            user.setGender(editDTO.getGender());
        }
        if (editDTO.getInterests() != null) {
            user.setInterests(editDTO.getInterests());
        }

        userRepository.save(user); // 수정된 사용자 정보를 저장
    }

    // 현재 인증된 사용자의 정보를 가져오는 메서드
    public UserDTO getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // 현재 사용자의 인증 정보를 가져옴

        String username = auth.getName(); // 현재 인증된 사용자의 이름을 가져옴

        // 현재 인증된 사용자를 데이터베이스에서 조회하여 가져옴
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User Entity Not Found")); // 사용자가 존재하지 않으면 예외 발생

        // 사용자 엔티티에서 DTO로 변환하여 반환
        UserDTO userDTO = new UserDTO();
        userDTO.setNumber(user.getNumber());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setAge(user.getAge());
        userDTO.setGender(user.getGender());
        userDTO.setInterests(user.getInterests());
        userDTO.setBio(user.getBio());
        return userDTO;
    }
}