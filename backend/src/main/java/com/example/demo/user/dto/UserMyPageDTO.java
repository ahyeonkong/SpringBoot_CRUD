package com.example.demo.user.dto;

import com.example.demo.user.domain.User;
import lombok.Getter;

@Getter
public class UserMyPageDTO {
    /*
        마이 페이지 조회를 위한 DTO (UserMyPageDTO)
    */
    private String name;
    private String img;
//    private String myPostTitle;

    public UserMyPageDTO(User user){
        this.name = user.getName();
        this.img = user.getImg();
//        this.myPostTitle = post.getTitle();
    }
}
