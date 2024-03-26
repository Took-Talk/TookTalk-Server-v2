package com.mirae.tooktalk.domain.user.payload.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtResponse {
    private final String type = "Bearer";
    private String token;
    private Long id;
    private String number;
    private String password;
    private List<String> roles;

    public static JwtResponse setJwtResponse(String token, Long id, String number, String password, List<String> roles) {
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.token = token;
        jwtResponse.id = id;
        jwtResponse.number = number;
        jwtResponse.password = password;
        jwtResponse.roles = roles;
        return jwtResponse;
    }
}