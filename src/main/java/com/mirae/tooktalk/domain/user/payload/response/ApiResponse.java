package com.mirae.tooktalk.domain.user.payload.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private T data;

    public static <T> ApiResponse<T> setApiResponse(Boolean success, String message, T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.success = success;
        apiResponse.message = message;
        apiResponse.data = data;
        return apiResponse;
    }
}