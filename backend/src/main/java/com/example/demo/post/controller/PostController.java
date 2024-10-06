package com.example.demo.post.controller;

import com.example.demo.post.domain.PostCreateRequest;
import com.example.demo.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller는 클라이언트의 요청을 받아 처리하는 진입점 역할을 한다.

@RestController
/*
    @RestController는 Spring MVC에서 사용되는 어노테이션으로 이 클래스가 REST API의 컨트롤러(RESTful 웹 서비스를 구현하는 컨트롤러)임을 나타냄
    내부적으로 @Controller와 @ResponseBody를 결합한 형태
        @Controller: 이 클래스가 웹 요청을 처리하는 컨트롤러임을 알림
        @ResponseBody: 메서드가 반환하는 값이 View를 통해 렌더링되지 않고, HTTP 응답의 본문에 직접 쓰인다는 것을 나타냄
    즉, 메서드가 반환하는 데이터는 JSON, XML 같은 형식으로 변환되어 클라이언트로 전달됨

    클라이언트 -> 서버 요청 : @RequestBody
    서버 -> 클라이언트 응답 : @ResponseBody
*/
@RequiredArgsConstructor
/*
    @RequiredArgsConstructor는 Lombok 라이브러리에서 제공하며, final로 선언된 모든 필드나 @NonNull로 선언된 필드에 대해 생성자를 자동으로 생성
    final 필드로 의존성을 주입할 때 주로 사용, 의존성 주입에서 생성자를 통해 주입받는 방식을 간편하게 만들기 위함
 */
@RequestMapping("/post")
/*
    @RequestMapping는 Spring MVC에서 요청 매핑을 설정하는 어노테이션
    특정 URL 패턴에 대해 이 컨트롤러가 처리할 요청들을 정의함.
    "/post" 경로로 오는 모든 요청을 이 컨트롤러에서 처리하게 만든다.
 */
@Slf4j // Logger 객체를 자동으로 생성해주어 log 인스턴스 사용 가능
public class PostController {
    @Autowired
    /*
        @Autowired는 Spring에서 의존성 주입을 수행할 때 사용하는 어노테이션
        스프링 컨테이너가 해당 필드, 생성자, 또는 메서드에 맞는 빈(Bean)을 찾아서 자동으로 주입
        객체 간의 결합도를 낮추고, 애플리케이션의 확장성을 높일 수 있음
        객체를 직접 생성하거나 관리할 필요 없이, 스프링이 필요한 빈을 주입하여 관리해줌
        필드 주입보다 생성자 주입을 권장함
     */
    private final PostService postService;
    /*
        @RequiredArgsConstructor에 의해 PostService를 생성자를 통해 자동으로 주입받는 생성자가 Lombok에 의해 자동 생성됨
        결과적으로 의존성 주입을 더 간편하게 처리할 수 있음
     */

    @PostMapping
    /*
        POST /post 요청을 처리함
        POST 요청은 주로 서버에 데이터를 전송하기 위해 사용
     */
    public ResponseEntity<Void> createPost(@RequestBody PostCreateRequest request){
        /*
            @RequestBody 어노테이션 누락: PostMapping 메서드에서 @RequestBody 어노테이션이 누락됨
            이로 인해 요청 본문의 JSON 데이터가 PostCreateRequest 객체로 제대로 변환되지 않음
            @RequestBody: 클라이언트가 보낸 HTTP 요청 본문(JSON)을 자바 객체로 변환
        */

        /*
            ResponseEntity<Void>와 ResponseEntity<String>의 차이
            ResponseEntity<Void>: 서버는 클라이언트에게 HTTP 상태 코드와 헤더만을 응답, 본문은 비어 있음
                                  클라이언트는 상태 코드만 확인하여 성공 여부를 알 수 있음.
            ResponseEntity<String>: 서버는 클라이언트에게 요청에 대한 처리 결과나 메시지를 본문에 포함해서 전달
            -> 어느 것을 사용할지는 API의 설계 의도에 따라 달라짐
        */
        
//        log.warn(request.getUserId().toString());

        postService.createPost(request);
        return ResponseEntity.noContent().build();
    }
}
