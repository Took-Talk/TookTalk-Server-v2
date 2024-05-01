package com.mirae.tooktalk.domain.user.service.user;


import com.mirae.tooktalk.global.exception.BusinessException;
import com.mirae.tooktalk.domain.user.payload.request.SignupRequest;

import java.io.IOException;

public interface UserService {

    void registerUser(SignupRequest signupRequest) throws BusinessException, IOException;
}