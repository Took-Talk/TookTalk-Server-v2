package com.mirae.tooktalk.domain.user.service.user;

import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
import com.mirae.tooktalk.domain.user.exception.CustomException;
import com.mirae.tooktalk.domain.user.payload.request.SignupRequest;
import com.mirae.tooktalk.domain.user.payload.request.UserInfoRequest;
import com.mirae.tooktalk.domain.user.payload.response.JwtResponse;
import com.mirae.tooktalk.domain.user.repository.user.UserRepository;
import com.mirae.tooktalk.domain.user.security.jwt.JwtUtils;
import com.mirae.tooktalk.domain.user.security.service.UserDetailsImpl;
import com.mirae.tooktalk.domain.user.service.role.RoleService;
import com.mirae.tooktalk.global.common.S3.S3Uploader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final S3Uploader s3Uploader;

    @Override
    @Transactional
    public void registerUser(SignupRequest signupRequest, MultipartFile multipartFile) throws CustomException, IOException {
        if (userRepository.existsByNumber(signupRequest.getNumber())) {
            throw new CustomException("이미 사용중인 전화번호 입니다.");
        }
        if (userRepository.existsByNickname(signupRequest.getNickname())) {
            throw new CustomException("이미 사용중인 닉네임 입니다.");
        }

        String url = s3Uploader.upload(multipartFile, "image");

        UserEntity user = UserEntity.registerUser(
                encoder.encode(signupRequest.getPassword()),signupRequest.getNumber(), signupRequest.getNickname(),
                signupRequest.getAge(),signupRequest.getMbti(), signupRequest.getGender(),
                signupRequest.getInterests(), signupRequest.getBio(), roleService.getDefaultRole(), 1, url
        );
        userRepository.save(user);
    }

    @Transactional
    public void updateUserData(UserInfoRequest request, String nickname, MultipartFile multipartFile){
        Optional<UserEntity> user = userRepository.findByNicknameEquals(nickname);

        user.ifPresent(value -> {
            value.fixUserData(
                    request.getNickname(),
                    request.getMbti(),
                    request.getBio()
            );

            try {
                uploadUserImage(value, multipartFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            userRepository.save(value);
        });
    }

    private void uploadUserImage(UserEntity user, MultipartFile multipartFile) throws IOException {
        user.fixImage(s3Uploader.upload(multipartFile, "profile pic"));
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