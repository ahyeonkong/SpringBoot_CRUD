package com.example.demo.user.dto;

import com.example.demo.post.domain.Post;
import com.example.demo.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserMyPageDTO {
    private final String name;
    private final String img;
    private final String userLoginId;
    private List<String> myPostTitles;

    public UserMyPageDTO(User user){
        this.name = user.getName();
        this.img = user.getImg();
        this.userLoginId = user.getUserLoginId();
    }
}