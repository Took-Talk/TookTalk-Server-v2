package com.mirae.tooktalk.global.common.S3;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> authenticateUser(@RequestPart("image") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok().body(s3Uploader.upload(multipartFile, "profile pic"));
    }
}