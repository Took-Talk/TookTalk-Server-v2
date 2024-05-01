package com.mirae.tooktalk.domain.user.repository.role;

import com.mirae.tooktalk.domain.user.entity.role.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}