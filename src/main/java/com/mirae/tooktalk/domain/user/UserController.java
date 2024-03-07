package com.mirae.tooktalk.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public String register(RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return "ok";
    }
}
