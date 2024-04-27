package com.mirae.tooktalk.domain.user.service.user;


import com.mirae.tooktalk.domain.user.exception.CustomException;
import com.mirae.tooktalk.domain.user.payload.request.SignupRequest;

import java.io.IOException;

public interface UserService {

    void registerUser(SignupRequest signupRequest) throws CustomException, IOException;
}