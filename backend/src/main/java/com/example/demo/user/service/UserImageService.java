package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.dto.UserImageUploadDTO;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class UserImageService {

    private final UserRepository userRepository;
    private static final String UPLOAD_DIR = "uploads/";

    public UserImageUploadDTO uploadUserImage(Long userId, MultipartFile file) throws IOException {
        // 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 이미지 파일 저장
        String fileName = saveImage(file);

        // DTO 생성
        UserImageUploadDTO userImageUploadDTO = new UserImageUploadDTO(userId, fileName);

        // 사용자 정보 업데이트
        updateUserImage(user, userImageUploadDTO);

        // DTO 반환
        return userImageUploadDTO;
    }

    private void updateUserImage(User user, UserImageUploadDTO dto) {
        user.setImg(dto.getImageName());
        userRepository.save(user);
    }

    private String saveImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }
}