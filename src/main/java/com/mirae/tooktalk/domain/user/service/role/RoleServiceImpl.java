package com.mirae.tooktalk.domain.user.service.role;

import com.mirae.tooktalk.domain.user.entity.role.RoleEntity;
import com.mirae.tooktalk.domain.user.enums.ERole;
import com.mirae.tooktalk.domain.user.repository.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Set<RoleEntity> getDefaultRole() {
        RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: 해당하는 권한이 없습니다."));
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(userRole);
        return roles;
    }
}