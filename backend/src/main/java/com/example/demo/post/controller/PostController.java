package com.example.demo.post.controller;

import com.example.demo.post.domain.Post;
import com.example.demo.post.dto.PostCreateRequestDTO;
import com.example.demo.post.dto.PostDetailDTO;
import com.example.demo.post.dto.PostMainPageDTO;
import com.example.demo.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//@RequestMapping("/post")
/*
    @RequestMapping는 Spring MVC에서 요청 매핑을 설정하는 어노테이션
    특정 URL 패턴에 대해 이 컨트롤러가 처리할 요청들을 정의함.
    "/post" 경로로 오는 모든 요청을 이 컨트롤러에서 처리하게 만든다.
    ---
    @RequestMapping("/post") 주석을 해제하면,
    @GetMapping("/main")의 실제 경로는 "/post/main"이 되기 때문에 경로를 /main으로 하려는 목적에 맞지 않다.
    따라서 주석 처리를 한다.
    ---
    @GetMapping("/main")을 MainController으로 경로를 분리했기 때문에 다시 작성해준다.
 */

@RequestMapping("/post")
    /*
        /post에서 POST 메서드, /post/{post_id}에서 GET 메서드를 사용하기 위해서는
        상위 페이지에 @RequestMapping("/post")로 페이지 매핑을 해준다.
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
    public ResponseEntity<Void> createPost(@RequestBody PostCreateRequestDTO request){
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

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailDTO> getDetailPost(@PathVariable Long postId){
        PostDetailDTO postDetailDTO = postService.getPostById(postId);
        return ResponseEntity.ok(postDetailDTO);
    }
    /*
        /{postId}는 경로 변수를 나타냄

        ResponseEntity<PostDetailDTO>: 서버는 클라이언트에게 요청에 대한 처리 결과나 메시지를 본문에 포함해서 응답함
                                       PostDetailDTO 타입의 본문을 포함
        getDetailPost() 메서드 사용: 게시물의 상세 정보(작성자, 제목, 본문) 가져오는 기능
        @PathVariable: 경로의 변수 부분({postId})을 메서드 파라미터 postId에 바인딩
                       URL의 {postId}와 메서드 파라미터의 변수 이름이 같으면 기본적으로 자동 매핑
        postService의 getPostById() 메서드를 호출하여 postId에 해당하는 게시물 상세 정보 가져옴
    */

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        boolean isDeleted = postService.deletePost(postId);
        if(isDeleted){
            return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    /*
        코드가 post 디렉토리 안에 있기 때문에, Post 엔티티의 id로 찾아 게시물을 삭제한다고 할 수 있다.

        JPA의 동작에 의해 다음과 같이 게시물이 삭제된다.
        * JPA는 @Id로 지정된 필드를 사용하여 데이터베이스에서 엔티티를 찾는다.
        * existsById는 주어진 ID를 가진 엔티티가 데이터베이스에 존재하는지 확인한다.
        * deleteById는 주어진 ID를 가진 엔티티를 데이터베이스에서 삭제한다.

        @Id 어노테이션의 역할에 의해 JPA가 다음과 같이 동작한다.
        * @Id는 엔티티 클래스의 필드를 데이터베이스 테이블의 기본 키(primary key)와 매핑한다.
        * 이 어노테이션이 붙은 필드는 엔티티의 고유 식별자로 사용된다.
     */
}
