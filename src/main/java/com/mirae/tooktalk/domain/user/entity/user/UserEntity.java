package com.mirae.tooktalk.domain.user.entity.user;

import com.mirae.tooktalk.domain.user.entity.role.RoleEntity;
import com.mirae.tooktalk.domain.user.entity.userroles.Userroles;
import com.mirae.tooktalk.domain.user.enums.*;
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
        닉네임
    */
    @Column(nullable = false)
    private String nickname;

    /*
        나이
    */
    @Column(nullable = false)
    private String age;

    /*
       성별
    */
    @Column(nullable = false)
    private String gender;

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

    public void hidePassword(String password) {
        this.password = password;
    }

    public static UserEntity registerUser(
            String password,
            String number,
            String nickname,
            String age,
            String mbti,
            String gender,
            List<String> interests,
            String bio,
            Set<RoleEntity> roles
    ) {
        UserEntity user = new UserEntity();
        user.password = password;
        user.number = number;
        user.nickname = nickname;
        user.gender = gender;
        user.age = age;
        user.mbti = mbti;
        user.interests = interests;
        user.bio = bio;

        for (RoleEntity role : roles) {
            Userroles.UserRoles userRoles = Userroles.UserRoles.createUserRoles(user, role);
            user.getRoles().add(userRoles);
        }

        return user;
    }

    public void fixUserData(String nickname, String gender, String age, String mbti, String bio) {
        this.nickname = nickname;
        this.gender = gender;
        this.age = age;
        this.mbti = mbti;
        this.bio = bio;
    }
}
