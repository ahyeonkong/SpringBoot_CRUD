package com.example.demo.post.controller;

import com.example.demo.post.dto.PostMainPageDTO;
import com.example.demo.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping
public class MainController {
    @Autowired
    private final PostService postService;

    @GetMapping("/main")
    /*
        @GetMapping 어노테이션은 HTTP GET 요청을 이 메서드에 매핑
        "/main" 경로로 들어오는 GET 요청을 이 메서드가 처리
    */
    public ResponseEntity<List<PostMainPageDTO>> getMainPagePosts(){
        List<PostMainPageDTO> posts = postService.getMainPagePosts();
        return ResponseEntity.ok(posts);
    }
    /*
        getMainPagePosts() 메서드는 ResponseEntity<List<PostMainPageDTO>> 타입을 반환, PostMainPageDTO 객체의 리스트를 반환
        ResponseEntity.ok()는 HTTP 상태 코드 200 (OK)와 함께 응답을 생성
        실제 데이터(posts)를 ResponseEntity에 포함시켜 반환
    */
}
