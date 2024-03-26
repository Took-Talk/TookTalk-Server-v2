package com.mirae.tooktalk.domain.user.service.role;


import com.mirae.tooktalk.domain.user.entity.role.RoleEntity;

import java.util.Set;

public interface RoleService {
    Set<RoleEntity> getDefaultRole();
}