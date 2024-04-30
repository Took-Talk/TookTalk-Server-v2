package com.mirae.tooktalk.domain.image.service;

import com.mirae.tooktalk.domain.user.exception.BusinessException;
import com.mirae.tooktalk.domain.user.exception.error.ErrorCode;
import com.mirae.tooktalk.global.common.S3.S3Uploader;
import com.mirae.tooktalk.global.common.S3.dto.S3Dto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final S3Uploader s3Uploader;

    public S3Dto uploadImage(MultipartFile multipartFile) {
        try {
            return S3Dto.builder().imgUrl(s3Uploader.upload(multipartFile, "picture")).build();
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_ERROR);
        }
    }
}

