package com.mirae.tooktalk.domain.user.enums;

public enum UserState {
    ACTIVE(0),
    INACTIVE(1),
    DELETED(2);

    private final int value;

    UserState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}