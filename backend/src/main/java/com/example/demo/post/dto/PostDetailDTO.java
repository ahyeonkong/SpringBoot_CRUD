package com.example.demo.post.dto;

import com.example.demo.post.domain.Post;
import lombok.Getter;

@Getter
public class PostDetailDTO {
    /*
    상세 페이지 조회를 위한 DTO (PostDetailDTO)
    이 DTO는 서버에서 클라이언트에게 상세 페이지 데이터를 보여줄 때 사용됨

        ex. 서버의 json 응답 (http://localhost:8080/post/8)
        {
            "title": "제목3",
            "text": "본문3",
            "userName": "akong"
        }
*/
    private final String title;
    private final String text;
    private final String userName;

    public PostDetailDTO(Post post, String title, String text) {
        this.userName = post.getUser().getName();
        this.title = title;
        this.text = text;
    }
}
