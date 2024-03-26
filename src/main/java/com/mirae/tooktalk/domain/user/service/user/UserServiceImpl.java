package com.mirae.tooktalk.domain.user.service.user;

import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
import com.mirae.tooktalk.domain.user.exception.CustomException;
import com.mirae.tooktalk.domain.user.payload.request.SignupRequest;
import com.mirae.tooktalk.domain.user.repository.user.UserRepository;
import com.mirae.tooktalk.domain.user.repository.userroles.UserRolesRepository;
import com.mirae.tooktalk.domain.user.service.role.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserRolesRepository userRolesRepository;

    private final RoleService roleService;

    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public void registerUser(SignupRequest signupRequest) throws CustomException {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new CustomException("이미 사용중인 이메일입니다.");
        }
        UserEntity user = UserEntity.registerUser(signupRequest.getEmail(), signupRequest.getUsername(), encoder.encode(signupRequest.getPassword()), roleService.getDefaultRole());
        userRepository.save(user);
    }

}
