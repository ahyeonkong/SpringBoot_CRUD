package com.example.demo.post.domain;

import lombok.Getter;
@Getter
public class PostMainPageDTO {
    /*
        메인 페이지 조회를 위한 응답 DTO (PostMainPageDTO)
        이 DTO는 서버에서 클라이언트에게 데이터를 보여줄 때 사용됨

        ex. 서버의 json 응답
        {
            "title": "제목",
            "userId": 1
        },
        {
            "title": "제목2",
            "userId": 1
        }
        
        현재 userId로 값을 반환하는데 추후 userName으로 변경할 예정!
    */

    private String title;
    private Long userId;
//    private String userName; 추후 사용자 이름 받아올 예정

    public PostMainPageDTO(Post post){
        this.title = post.title;
        this.userId = post.user.getId(); // Post 객체가 User 객체와 연관관계가 있음
    }


//    public PostMainPageDTO(Post post, String userName){
//        this.title = post.title;
//        this.userName = userName;
//    }
}
