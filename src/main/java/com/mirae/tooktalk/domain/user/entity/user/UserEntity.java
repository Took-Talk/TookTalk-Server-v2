package com.mirae.tooktalk.domain.user.entity.user;

import com.mirae.tooktalk.domain.user.entity.role.RoleEntity;
import com.mirae.tooktalk.domain.user.entity.userroles.Userroles;
import com.mirae.tooktalk.domain.user.enums.UserState;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*
        닉네임
    */
    @Column(nullable = false)
    private String username;

    /*
        전화번호
    */
    @Column(nullable = false)
    private String number;

    /*
        비밀번호
    */
    @Column(nullable = false)
    private String password;

    /*
        나이
    */
    @Column(nullable = false)
    private String age;

    /*
       mbti
    */
    @Column(nullable = false)
    private String mbti;

    /*
        관심사
    */
    @ElementCollection
    private List<String> interests;

    /*
        자기소개
    */
    @Column(nullable = false)
    private String bio;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Userroles.UserRoles> roles = new HashSet<>();

    private Integer status;

    public static UserEntity registerUser(
            String username,
            String password,
            String number,
            String age,
            String mbti,
            List<String> interests,
            String bio,
            Set<RoleEntity> roles
    ) {
        UserEntity user = new UserEntity();
        user.username = username;
        user.password = password;
        user.number = number;
        user.age = age;
        user.mbti = mbti;
        user.interests = interests;
        user.bio = bio;
        user.status = UserState.ACTIVE.getValue();

        for (RoleEntity role : roles) {
            Userroles.UserRoles userRoles = Userroles.UserRoles.createUserRoles(user, role);
            user.getRoles().add(userRoles);
        }

        return user;
    }
}
