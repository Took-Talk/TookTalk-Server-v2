package com.mirae.tooktalk.domain.user.service.user;


import com.mirae.tooktalk.domain.user.exception.CustomException;
import com.mirae.tooktalk.domain.user.payload.request.SignupRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    void registerUser(SignupRequest signupRequest, MultipartFile multipartFile) throws CustomException, IOException;
}