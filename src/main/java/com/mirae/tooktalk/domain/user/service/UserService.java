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

    public void editUser(EditDTO editDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User Entity Not Found"));

        user.setUsername(editDTO.getName());

        userRepository.save(user);
    }

    public UserDTO getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User Entity Not Found"));
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
