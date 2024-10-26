package com.example.demo.user.controller;

import com.example.demo.user.dto.UserImageUploadDTO;
import com.example.demo.user.service.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class UserImageController {

    private final UserImageService userImageService;

    @PostMapping("/{userId}/image")
    public ResponseEntity<?> uploadImage(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        try {
            UserImageUploadDTO dto = userImageService.uploadUserImage(userId, file);
            return ResponseEntity.ok(dto);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("이미지 업로드 중 오류가 발생했습니다: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}