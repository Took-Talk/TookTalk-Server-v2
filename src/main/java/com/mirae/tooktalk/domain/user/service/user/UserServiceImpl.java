package com.mirae.tooktalk.domain.user.service.user;

import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
import com.mirae.tooktalk.domain.user.exception.BusinessException;
import com.mirae.tooktalk.domain.user.exception.error.ErrorCode;
import com.mirae.tooktalk.domain.user.payload.request.SignupRequest;
import com.mirae.tooktalk.domain.user.payload.request.UserInfoRequest;
import com.mirae.tooktalk.domain.user.payload.response.JwtResponse;
import com.mirae.tooktalk.domain.user.repository.user.UserRepository;
import com.mirae.tooktalk.domain.user.security.jwt.JwtUtils;
import com.mirae.tooktalk.domain.user.security.service.UserDetailsImpl;
import com.mirae.tooktalk.domain.user.service.role.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Override
    @Transactional
    public void registerUser(SignupRequest signupRequest) throws BusinessException {
        if (userRepository.existsByNumber(signupRequest.getNumber())) {
            throw new BusinessException(ErrorCode.NUMBER_BAD_REQUEST);
        }
        if (userRepository.existsByNickname(signupRequest.getNickname())) {
            throw new BusinessException(ErrorCode.NiCKNAME_BAD_REQUEST);
        }

        UserEntity user = UserEntity.registerUser(
                encoder.encode(signupRequest.getPassword()),signupRequest.getNumber(), signupRequest.getNickname(),
                signupRequest.getAge(),signupRequest.getMbti(), signupRequest.getGender(),
                signupRequest.getInterests(), signupRequest.getBio(), roleService.getDefaultRole(), 1, signupRequest.getImgUrl()
        );
        userRepository.save(user);
    }

    @Transactional
    public void updateUserData(UserInfoRequest request, String nickname){
        UserEntity user = userRepository.findByNicknameEquals(nickname)
                .orElseThrow(()-> new BusinessException(ErrorCode.NOT_FOUND));

        user.fixUserData(
                request.getNickname(),
                    request.getMbti(),
                    request.getBio(),
                    request.getImgUrl()
        );
    }

    /* 인증 및 JWT 토큰 생성 */
    public JwtResponse authenticateAndGenerateJWT(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roleNames = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return JwtResponse.setJwtResponse(jwt, (long) userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roleNames);
    }
}