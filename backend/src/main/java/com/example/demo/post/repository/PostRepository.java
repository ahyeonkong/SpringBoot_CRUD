package com.example.demo.post.repository;

import com.example.demo.post.domain.Post;
import com.example.demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// JPA를 사용하여 Post 엔티티에 대한 CRUD(생성, 읽기, 업데이트, 삭제) 작업을 수행하는 Repository 인터페이스를 정의함
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}
/*
    PostRepository 인터페이스는 JpaRepository를 상속받아 Post 엔티티에 대한 데이터베이스 작업을 처리하는 레포지토리 역할을 함
    JpaRepository를 상속함으로써, PostRepository는 기본적인 CRUD 메서드를 자동으로 제공하며 다음과 같은 메서드들이 포함됨

        * 게시글은 여기서 Post 엔티티이다.
        save(Post post): 게시글 저장 또는 업데이트
        saveAll(Iterable<Post> posts): 여러 게시글 한 번에 저장 또는 업데이트
        findById(Long id): ID로 게시글 조회. Optional<Post> 반환
        existsById(Long id): 해당 ID의 게시글 존재 여부 확인. boolean 반환
        findAll(): 모든 게시글 조회
        findAllById(Iterable<Long> ids): 주어진 ID 목록에 해당하는 게시글들 조회
        count(): 전체 게시글 수 반환
        deleteById(Long id): ID로 게시글 삭제
        delete(Post post): 주어진 게시글 삭제
        deleteAll(): 모든 게시글 삭제
        deleteAll(Iterable<Post> posts): 주어진 게시글들 모두 삭제
        flush(): 변경 사항 데이터베이스에 즉시 반영
        saveAndFlush(Post post): 게시글 저장하고 즉시 flush
        getOne(Long id): ID로 게시글 참조 가져옴. 실제 데이터는 사용 시점에 로딩


    이외에도 추가적인 메서드를 정의할 수 있으며 예를 들어, 게시물 제목이나 작성자에 따라 게시물을 검색하는 메서드를 추가할 수 있음
*/