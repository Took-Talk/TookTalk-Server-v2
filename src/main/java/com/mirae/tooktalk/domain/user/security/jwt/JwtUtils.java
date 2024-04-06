package com.mirae.tooktalk.domain.user.security.jwt;

import com.mirae.tooktalk.domain.user.security.service.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private final SecretKey key;
    @Value("${spring.jwt.jwtExpirationMs}")
    private int jwtExpirationMs;

    public JwtUtils(@Value("${spring.jwt.secret}") String jwtSecret) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .subject(userPrincipal.getEmail())
                .claim("roles", userPrincipal.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean vaildateJwtToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("잘못된 JWT 토큰: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT 토큰이 만료되었습니다: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("지원되지 않는 JWT 토큰입니다: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT 클레임 문자열이 비어 있습니다: {}", e.getMessage());
        }
        return false;
    }
}