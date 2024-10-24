package com.example.demo.post.dto;

import com.example.demo.post.domain.Post;
import lombok.Getter;
@Getter
public class PostMainPageDTO {
    /*
        메인 페이지 조회를 위한 DTO (PostMainPageDTO)
        이 DTO는 서버에서 클라이언트에게 데이터를 보여줄 때 사용됨

        ex. 서버의 json 응답
        title과 userName 데이터 받아오기
        {
            "title": "제목",
            "userName": "akong"
        },
        {
            "title": "제목2",
            "userName": "akong"
        }
    */

    private final String title;
    private final String userName;

    public PostMainPageDTO(Post post) {
        this.title = post.getTitle();
        this.userName = post.getUser().getName(); // Post 객체가 User 객체와 연관관계가 있음
    }
}
