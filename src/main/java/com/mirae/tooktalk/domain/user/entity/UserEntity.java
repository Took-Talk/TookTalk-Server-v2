package com.mirae.tooktalk.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
        성별
    */
    @Column(nullable = false)
    private String gender;

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

    /*
        권한 정보 (임시)
    */
    @Column(nullable = false)
    private String role;

    @Builder
    public UserEntity(
            String username,
            String password,
            String number,
            String age,
            String gender,
            List<String> interests,
            String bio,
            String role
    )
    {
        this.username = username;
        this.password = password;
        this.number = number;
        this.age = age;
        this.gender = gender;
        this.interests = interests;
        this.bio = bio;
        this.role = role;
    }

    public void editUser(String username, String password, String number,
                         String age, String gender, List<String> interests, String bio) {
        this.username = username;
        this.password = password;
        this.number = number;
        this.age = age;
        this.gender = gender;
        this.interests = interests;
        this.bio = bio;
    }
}
