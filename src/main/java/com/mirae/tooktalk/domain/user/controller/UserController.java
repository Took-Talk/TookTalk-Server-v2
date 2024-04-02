package com.mirae.tooktalk.domain.user.controller;

import com.mirae.tooktalk.domain.user.exception.CustomException;
import com.mirae.tooktalk.domain.user.payload.request.LoginRequest;
import com.mirae.tooktalk.domain.user.payload.request.SignupRequest;
import com.mirae.tooktalk.domain.user.payload.response.ApiResponse;
import com.mirae.tooktalk.domain.user.payload.response.JwtResponse;
import com.mirae.tooktalk.domain.user.payload.response.UserInfoResponse;
import com.mirae.tooktalk.domain.user.security.jwt.JwtUtils;
import com.mirae.tooktalk.domain.user.security.service.UserDetailsImpl;
import com.mirae.tooktalk.domain.user.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "유저", description = "유저 관련 api 입니다.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtUtils jwtUtils;

    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(authenticateAndGenerateJWT(loginRequest.getNumber(), loginRequest.getPassword()));
    }

    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    @PostMapping("/signup")
    public ResponseEntity<?> registerAndAuthenticateUser(@RequestBody SignupRequest signupRequest) throws CustomException {

        /* 유저 등록 */
        userService.registerUser(signupRequest);

        JwtResponse jwtResponse = authenticateAndGenerateJWT(signupRequest.getNumber(), signupRequest.getPassword());
        ApiResponse<JwtResponse> response = ApiResponse.setApiResponse(true, "회원 가입이 완료 되었습니다!", jwtResponse);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "유저 정보 제공", description = "토큰을 이용하여 유저 정보를 제공합니다.")
    @GetMapping("/user-info")
    public UserInfoResponse provideUserInfo(@RequestParam String token) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();

        return userInfoResponse;
    }

    /* 인증 및 JWT 토큰 생성 */
    private JwtResponse authenticateAndGenerateJWT(String email, String password) {
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