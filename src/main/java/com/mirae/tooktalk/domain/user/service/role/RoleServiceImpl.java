package com.mirae.tooktalk.domain.user.service.role;

import com.mirae.tooktalk.domain.user.entity.role.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Override
    public Set<RoleEntity> getDefaultRole() {
        Set<RoleEntity> roles = new HashSet<>();
        return roles;
    }
}