package com.mirae.tooktalk.domain.user.controller;

import com.mirae.tooktalk.domain.user.dto.UserDTO;
import com.mirae.tooktalk.domain.user.service.UserService;
import com.mirae.tooktalk.domain.user.dto.EditDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        userService.register(userDTO);
        return "ok";
    }

    @PatchMapping("/edit")
    public String editUser(@RequestBody EditDTO editDTO) {
        userService.editUser(editDTO);

        return "";
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInfo() {
        return ResponseEntity.ok(userService.getUser());
    }
}