package com.example.demo.post.service;

import com.example.demo.post.domain.Post;
import com.example.demo.post.dto.PostCreateRequestDTO;
import com.example.demo.post.dto.PostDetailDTO;
import com.example.demo.post.dto.PostMainPageDTO;
import com.example.demo.post.repository.PostRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/*
    Service는 비즈니스 로직을 처리하는 계층
    Service의 역할은 Dao가 DB에서 받아온 데이터를 전달받아 가공하는 것
    게시물 생성 기능을 제공하며, 주로 레포지토리와 상호작용하여 데이터베이스 작업을 수행함
 */

@Service // @Service 어노테이션으로 스프링 컨테이너에 빈으로 등록됨. Spring의 서비스 컴포넌트임을 나타냄
@RequiredArgsConstructor
/*
    @RequiredArgsConstructor는 Lombok의 어노테이션으로, final 필드에 대한 생성자를 자동으로 생성함
*/
public class PostService {

    private final PostRepository postRepository; // Post 엔티티에 대한 CRUD 작업을 처리하는 레포지토리
    private final UserRepository userRepository; // User 엔티티에 대한 CRUD 작업을 처리하는 레포지토리
    /*
        PostRepository와 UserRepository는 @RequiredArgsConstructor에 의해 생성자 주입을 통해 주입됨
    */

    public void createPost(PostCreateRequestDTO request) { // createPost 메서드는 게시물을 생성하는 기능을 수행
        User user = userRepository.findById(request.getUserId()).get();
        /*
            주어진 userId를 사용하여 해당 사용자를 조회
            userRepository.findById()는 Optional<User>를 반환하므로, get() 메서드를 호출하여 User 객체를 가져옴
        */

        Post post123 = new Post(); // 새 Post 객체를 생성
        post123.setText(request.getText());
        post123.setTitle(request.getTitle());
        post123.setUser(user);

        postRepository.save(post123); // postRepository를 사용하여 새 게시물을 데이터베이스에 저장
    }

    public List<PostMainPageDTO> getMainPagePosts() {
        List<Post> posts = postRepository.findAll();
        /*
            findAll() 메서드를 호출하여 모든 게시물을 데이터베이스에서 가져온다.
        */

        return posts.stream()
                .map(PostMainPageDTO::new)
                .collect(Collectors.toList());
        /*
            posts.stream(): Post 객체의 리스트를 스트림으로 변환
            .map(PostMainPageDTO::new): map 연산을 사용하여 각 Post 객체를 PostMainPageDTO 객체로 변환
                                        PostMainPageDTO::new는 메서드 레퍼런스로, PostMainPageDTO 클래스의 생성자를 참조함
                                        Post 객체를 인자로 받아 PostMainPageDTO 객체를 생성
            .collect(Collectors.toList()): 변환된 PostMainPageDTO 객체들을 새로운 List로 수집

            Collectors란 Stream을 일반적인 List, Set등으로 변경시키는 Stream 메서드
        */
    }

    public PostDetailDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시물이 없습니다."));
        return new PostDetailDTO(post, post.getTitle(), post.getText());
    }
    /*
         postRepository의 findById() 메서드 호출하여 id를 통해 해당 게시물 조회
         이 메서드는 Optional<Post>를 반환함
         Optional이 비어있을 경우(게시물이 없는 경우) 예외를 던짐 (404)

         조회된 Post 객체를 사용하여 새로운 PostDetailDTO 객체를 생성
         생성자에 post 객체 전체(게시물 id 찾음)와 함께 post.getTitle()과 post.getText()를 전달
         생성된 DTO 객체를 반환
    */

    public boolean deletePost(Long postId){
        if(postRepository.existsById(postId)){
            postRepository.deleteById(postId);
            return true;
        }
        return false;
    }






}





