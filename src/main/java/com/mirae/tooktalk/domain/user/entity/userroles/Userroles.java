package com.mirae.tooktalk.domain.user.entity.userroles;

import com.mirae.tooktalk.domain.user.entity.role.RoleEntity;
import com.mirae.tooktalk.domain.user.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Userroles {
    @Entity
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Table(name = "user_roles")
    public static class UserRoles {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private UserEntity user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "role_id")
        private RoleEntity role;


        public static UserRoles createUserRoles(UserEntity user, RoleEntity role) {
            UserRoles userRoles = new UserRoles();
            userRoles.user = user;
            userRoles.role = role;
            return userRoles;
        }

    }
}
