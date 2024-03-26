package com.mirae.tooktalk.domain.user.repository.role;

import com.mirae.tooktalk.domain.user.enums.ERole;
import com.mirae.tooktalk.domain.user.entity.role.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(ERole name);
}