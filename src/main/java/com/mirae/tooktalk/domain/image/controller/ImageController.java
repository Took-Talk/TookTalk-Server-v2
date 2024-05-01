package com.mirae.tooktalk.domain.image.controller;

import com.mirae.tooktalk.domain.image.service.ImageService;
import com.mirae.tooktalk.domain.image.dto.ImageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "파일", description = "파일 업로드 api 입니다.")
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "업로드", description = "파일을 업로드 합니다.")
    @PostMapping("/upload")
    public ResponseEntity<ImageDTO> uploadImage(@RequestPart("image") MultipartFile multipartFile) {
        return ResponseEntity.ok().body(imageService.uploadImage(multipartFile));
    }
}