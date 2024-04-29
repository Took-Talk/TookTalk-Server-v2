package com.mirae.tooktalk.global.common.S3;

import com.mirae.tooktalk.global.common.S3.dto.S3Dto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "파일", description = "파일 업로드 api 입니다.")
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Uploader s3Uploader;

    @Operation(summary = "업로드", description = "파일을 업로드 합니다.")
    @PostMapping("/upload")
    public ResponseEntity<S3Dto> authenticateUser(@RequestPart("image") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok().body(S3Dto.builder().imgUrl(s3Uploader.upload(multipartFile, "picture")).build());
    }
}