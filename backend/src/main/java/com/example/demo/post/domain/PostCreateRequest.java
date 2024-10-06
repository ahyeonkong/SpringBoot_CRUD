package com.example.demo.post.domain;

import lombok.Getter;

@Getter // @Getter는 getter 메서드를 자동으로 생성
public class PostCreateRequest {
    /*
        PostCreateRequest라는 DTO 설정
        이 DTO는 주로 클라이언트가 서버로 데이터를 보낼 때 사용되며, 게시물 생성을 요청할 때 필요한 데이터를 담고 있음

        이 클래스는 데이터베이스에 저장되는 엔티티가 아니며, 클라이언트와 서버 간에 데이터를 전달하기 위한 용도로 사용됨
        클라이언트는 게시물 생성 요청 시 이 PostCreateRequest 객체의 형태로 데이터를 보내며, 서버는 이 데이터를 이용해 새로운 게시물을 생성함
        
        ex. 클라이언트의 json 요청
        {
            "title": "bbb",
            "text": "bbbbbbbbbbbbbbbbbbbb",
            "userId": 1
        }
    */
    private String title;
    private String text;
    private Long userId; // 클라이언트가 서버에 이 게시물을 요청할 때 어떤 사용자가 이 게시물을 작성했는지를 서버가 알 수 있게 도와줌
}
