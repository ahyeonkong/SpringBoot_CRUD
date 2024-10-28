package com.example.demo.post.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostUpdateRequestDTO {
    /*
        게시글 수정을 위한 DTO (PostUpdateRequestDTO)
        이 DTO는 클라이언트가 게시글 수정 요청 시 서버로 수정된 json을 요청하고 서버는 변경된 내용을 json으로 응답한다.

        ex. 클라이언트의 json 요청
        {
          "title": "제목12",
          "text": "본문12"
        }

        ex. 서버의 json 응답
        {
          "title": "제목12",
          "text": "본문12",
          "userId": 1
        }
    */

    private String title;
    private String text;
    private Long userId;

    public PostUpdateRequestDTO(String title, String text, Long userId) {
        this.title = title;
        this.text = text;
        this.userId = userId;
    }
}
