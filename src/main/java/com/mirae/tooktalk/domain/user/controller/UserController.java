package com.mirae.tooktalk.domain.user.controller;

import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
import com.mirae.tooktalk.global.exception.BusinessException;
import com.mirae.tooktalk.domain.user.payload.request.LoginRequest;
import com.mirae.tooktalk.domain.user.payload.request.SignupRequest;
import com.mirae.tooktalk.domain.user.payload.request.UserInfoRequest;
import com.mirae.tooktalk.domain.user.payload.response.ApiResponse;
import com.mirae.tooktalk.domain.user.payload.response.JwtResponse;
import com.mirae.tooktalk.domain.user.repository.user.UserRepository;
import com.mirae.tooktalk.domain.user.service.user.UserService;
import com.mirae.tooktalk.domain.user.service.user.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "유저", description = "유저 관련 api 입니다.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserServiceImpl userServiceImpl;

    private final UserRepository userRepository;

    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(userServiceImpl.authenticateAndGenerateJWT(loginRequest.getNumber(), loginRequest.getPassword()));
    }

    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    @PostMapping("/signup")
    public ResponseEntity<?> registerAndAuthenticateUser(
            @RequestBody SignupRequest signupRequest
            ) throws BusinessException, IOException {

        /* 유저 등록 */
        userService.registerUser(signupRequest);

        JwtResponse jwtResponse = userServiceImpl.authenticateAndGenerateJWT(signupRequest.getNumber(), signupRequest.getPassword());
        ApiResponse<JwtResponse> response = ApiResponse.setApiResponse(true, "회원 가입이 완료 되었습니다!", jwtResponse);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "프로필", description = "토큰을 이용하여 유저 정보를 조회합니다.")
    @GetMapping("/userinfo")
    public UserEntity provideUserInfo(Authentication authentication) {
        UserEntity userEntity = userRepository.findByNicknameEquals(authentication.getName()).get();

        userEntity.hidePassword("");

        return userEntity;
    }

    @Operation(summary = "프로필 수정", description = "유저 정보를 수정합니다.")
    @PutMapping("/userfix")
    public void fixUserData(
            @RequestBody UserInfoRequest userInfoRequest,
            Authentication authentication) {
        String userName = authentication.getName();
        userServiceImpl.updateUserData(userInfoRequest, userName);
        ResponseEntity.ok().body("");
    }
}
