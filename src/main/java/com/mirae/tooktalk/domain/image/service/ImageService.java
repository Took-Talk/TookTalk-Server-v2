package com.mirae.tooktalk.domain.image.service;

import com.mirae.tooktalk.global.exception.BusinessException;
import com.mirae.tooktalk.global.exception.error.ErrorCode;
import com.mirae.tooktalk.global.common.S3.S3Uploader;
import com.mirae.tooktalk.domain.image.dto.ImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final S3Uploader s3Uploader;

    public ImageDTO uploadImage(MultipartFile multipartFile) {
        try {
            return ImageDTO.builder().imgUrl(s3Uploader.upload(multipartFile, "picture")).build();
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_ERROR);
        }
    }
}

