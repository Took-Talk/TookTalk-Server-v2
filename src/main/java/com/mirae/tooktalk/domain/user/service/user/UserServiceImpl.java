package com.mirae.tooktalk.domain.user.service.user;

import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
import com.mirae.tooktalk.domain.user.exception.CustomException;
import com.mirae.tooktalk.domain.user.payload.request.SignupRequest;
import com.mirae.tooktalk.domain.user.repository.user.UserRepository;
import com.mirae.tooktalk.domain.user.service.role.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public void registerUser(SignupRequest signupRequest) throws CustomException {
        if (userRepository.existsByNumber(signupRequest.getNumber())) {
            throw new CustomException("이미 사용중인 전화번호 입니다.");
        }
        if (userRepository.existsByNickname(signupRequest.getNickname())) {
            throw new CustomException("이미 사용중인 닉네임 입니다.");
        }
        UserEntity user = UserEntity.registerUser(
                encoder.encode(signupRequest.getPassword()),signupRequest.getNumber(), signupRequest.getNickname(),
                signupRequest.getAge(), signupRequest.getGender(), signupRequest.getMbti(),
                signupRequest.getInterests(), signupRequest.getBio(), roleService.getDefaultRole()
            );
        userRepository.save(user);
    }

    @Transactional
    public void fixUserData(UserEntity dto, String number) {
        Optional<UserEntity> user = userRepository.findByNumber(number);
        user.ifPresent(value -> value.fixUserData(
                dto.getNickname(),
                dto.getNumber(),
                dto.getAge(),
                dto.getGender(),
                dto.getMbti(),
                dto.getBio()
        ));
        userRepository.save(user.get());
    }
}