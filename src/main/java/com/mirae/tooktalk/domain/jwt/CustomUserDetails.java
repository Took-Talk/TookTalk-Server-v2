package com.mirae.tooktalk.domain.jwt;

import lombok.RequiredArgsConstructor;
import com.mirae.tooktalk.domain.user.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>(); // 권한 정보를 저장할 컬렉션 생성

        collection.add((GrantedAuthority) userEntity::getRole); // 사용자의 역할(role)을 GrantedAuthority로 변환하여 컬렉션에 추가

        return collection; // 권한 정보 컬렉션 반환
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    } // 사용자의 비밀번호 반환

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    } // 사용자명 반환

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } // 계정 만료 여부, 현재 구현에서는 항상 true 반환

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } // 계정 잠금 여부, 현재 구현에서는 항상 true 반환

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } // 자격 증명 만료 여부, 현재 구현에서는 항상 true 반환

    @Override
    public boolean isEnabled() {
        return true;
    } // 계정 활성화 여부, 현재 구현에서는 항상 true 반환
}
