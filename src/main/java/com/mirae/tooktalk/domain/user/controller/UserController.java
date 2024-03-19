package com.mirae.tooktalk.domain.user.controller;

import com.mirae.tooktalk.domain.user.dto.UserDTO;
import com.mirae.tooktalk.domain.user.service.UserService;
import com.mirae.tooktalk.domain.user.dto.EditDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "유저", description = "유저 관련 API 모음")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "유저 회원가입을 진행합니다.")
    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        userService.register(userDTO);
        return "{}";
    }

    @Operation(summary = "프로필 수정", description = "프로필을 수정합니다.")
    @PatchMapping("/edit")
    public String editUser(@RequestBody EditDTO editDTO) {
        userService.editUser(editDTO);

        return "";
    }

    @Operation(summary = "마이페이지", description = "회원 정보를 조회합니다.")
    @GetMapping("/info")
    public ResponseEntity<?> getInfo() {
        return ResponseEntity.ok(userService.getUser());
    }
}