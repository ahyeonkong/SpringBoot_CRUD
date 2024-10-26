package com.example.demo.user.dto;

import lombok.Getter;

@Getter
public class UserImageUploadDTO {
    /*
        마이 페이지 내 유저 이미지 업로드 위한 DTO (UserImageUploadDTO)
    */
    private Long id; // 시퀀스 id로 받기
    private String imageName;

    public UserImageUploadDTO(Long id, String imageName){
        this.id = id;
        this.imageName = imageName;
    }

}
