package com.example.demo.post.dto;

import com.example.demo.post.domain.Post;
import lombok.Getter;

@Getter
public class PostDetailDTO {
    /*
    상세 페이지 조회를 위한 DTO (PostDetailDTO)
    이 DTO는 서버에서 클라이언트에게 상세 페이지 데이터를 보여줄 때 사용됨

        ex. 서버의 json 응답 (http://localhost:8080/post/6)
        {
            "title": "제목2",
            "text": "본문2",
            "userId": 1
        }
*/
    private String title;
    private String text;
    private Long userId;

    public PostDetailDTO(Post post, String title, String text) {
        this.userId = post.getUser().getId();
        this.title = title;
        this.text = text;
    }
}
