package com.mirae.tooktalk.domain.user.service.user;


import com.mirae.tooktalk.domain.user.exception.CustomException;
import com.mirae.tooktalk.domain.user.payload.request.SignupRequest;

public interface UserService {

    void registerUser(SignupRequest signupRequest) throws CustomException;
}