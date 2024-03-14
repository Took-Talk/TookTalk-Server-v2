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
    private List<String> interests;

    /*
        자기소개
    */
    @Column(nullable = false)
    private String bio;

    @Builder
    public UserEntity(
            String username,
            String password,
            String number,
            String age,
            String gender,
            String bio
    )
    {
        this.username = username;
        this.password = password;
        this.number = number;
        this.age = age;
        this.gender = gender;
        this.bio = bio;
    }
}
