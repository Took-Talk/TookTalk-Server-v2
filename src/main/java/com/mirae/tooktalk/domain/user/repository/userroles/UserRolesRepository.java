package com.mirae.tooktalk.domain.user.repository.userroles;


import com.mirae.tooktalk.domain.user.entity.userroles.userroles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<userroles.UserRoles, Long> {

}