package com.example.demo.user.domain;

import com.example.demo.post.domain.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter // @Getter를 사용하는게 맞을까?
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id; // 시퀀스 ID
    @Column
    String name; // 유저 실명
    @Column
    String img; // 프로필 이미지 파일 이름
    @Column
    String userLoginId; // 유저 로그인 아이디

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> postList = new ArrayList<>();

}
