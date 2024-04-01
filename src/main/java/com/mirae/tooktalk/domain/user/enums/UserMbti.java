package com.mirae.tooktalk.domain.user.enums;


public enum UserMbti {
    ISTP("ISTP"),
    ISTJ("ISTJ"),
    ISFP("ISFP"),
    ISFJ("ISFJ"),
    INTP("INTP"),
    INTJ("INTJ"),
    INFP("INFP"),
    INFJ("INFJ"),
    ESTP("ESTP"),
    ESTJ("ESTJ"),
    ESFP("ESFP"),
    ESFJ("ESFJ"),
    ENTP("ENTP"),
    ENTJ("ENTJ"),
    ENFP("ENFP"),
    ENFJ("ENFJ");

    private final String description;

    UserMbti(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
