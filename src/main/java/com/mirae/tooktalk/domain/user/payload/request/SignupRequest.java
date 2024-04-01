package com.mirae.tooktalk.domain.user.payload.request;

import com.mirae.tooktalk.domain.user.enums.Interest;
import com.mirae.tooktalk.domain.user.enums.UserGender;
import com.mirae.tooktalk.domain.user.entity.user.UserInterest;
import com.mirae.tooktalk.domain.user.enums.UserMbti;
import lombok.Data;

@Data
public class SignupRequest {

    private String number;

    private String password;

    private String nickname;

    private UserGender gender;

    private String age;

    private UserMbti mbti;

    private UserInterest<Interest> interests;

    private String bio;

    public UserGender getGender() {
        return gender;
    }

    public UserMbti getMbti() {
        return mbti;
    }

    public void setMbti(UserMbti mbti) {
        this.mbti = mbti;
    }

}