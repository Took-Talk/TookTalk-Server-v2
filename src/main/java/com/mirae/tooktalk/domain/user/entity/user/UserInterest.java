package com.mirae.tooktalk.domain.user.entity.user;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserInterest<T extends Enum<T>> {
    private T[] interests;

    public UserInterest(Class<T> enumType) {
        this.interests = enumType.getEnumConstants();
    }

    public T[] getInterests() {
        return interests;
    }

    public void setInterests(T[] interests) {
        this.interests = interests;
    }
}
