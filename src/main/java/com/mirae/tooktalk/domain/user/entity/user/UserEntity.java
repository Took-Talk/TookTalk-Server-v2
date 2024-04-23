package com.mirae.tooktalk.domain.user.entity.user;

import com.mirae.tooktalk.domain.user.entity.role.RoleEntity;
import com.mirae.tooktalk.domain.user.entity.userroles.Userroles;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
        프로필 사진
    */
    @Column
    private String imgUrl;

    /*
        자기소개
    */
    @Column(nullable = false)
    private String bio;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private int status; // 1 비활 2 활성

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
            Set<RoleEntity> roles,
            int status,
            String imgUrl
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
        user.status = status;
        user.imgUrl = imgUrl;

        for (RoleEntity role : roles) {
            Userroles.UserRoles userRoles = Userroles.UserRoles.createUserRoles(user, role);
            user.getRoles().add(userRoles);
        }
        return user;
    }

    public void fixUserData(String nickname, String mbti, String bio) {
        this.nickname = nickname;
        this.mbti = mbti;
        this.bio = bio;
    }

    public void fixImage(String imgUrl){
        this.imgUrl = imgUrl;
    }

    /* 유저 정보 반환 시 패스워드 공백 처리 */
    public static void hideUserPassword(UserEntity user) {
        user.hidePassword("");
    }
}
