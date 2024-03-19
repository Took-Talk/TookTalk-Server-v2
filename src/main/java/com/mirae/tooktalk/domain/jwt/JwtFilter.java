package com.mirae.tooktalk.domain.jwt;

import com.mirae.tooktalk.domain.user.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    // 필터링 작업 수행 메서드
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        // Authorization 정보가 없거나 Bearer 토큰 형식이 아닌 경우
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("token null");
            filterChain.doFilter(request, response);

            return;
        }

        // Bearer 토큰 형식의 토큰 문자열 추출
        String token = authorization.split(" ")[1];

        // 토큰이 만료 여부 확인
        if (jwtUtil.isExpired(token)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);

            return;
        }

        // 토큰으로부터 사용자명을 추출하여 CustomUserDetails 객체 생성
        CustomUserDetails customUserDetails = new CustomUserDetails(UserEntity.builder()
                .username(jwtUtil.getUsername(token)) // 토큰에서 사용자명 추출
                .password("12345678") // 임시 설정 비밀번호 (사용되지 않음)
                .role(jwtUtil.getRole(token))
                .build()
        );

        // 사용자 정보를 기반으로 Authentication 객체 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        // Spring Security의 SecurityContext에 Authentication 객체 설정
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response); // 다음 필터로 요청과 응답을 전달하고 메서드 종료
    }
}
