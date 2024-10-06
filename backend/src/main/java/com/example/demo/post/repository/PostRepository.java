package com.example.demo.post.repository;

import com.example.demo.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA를 사용하여 Post 엔티티에 대한 CRUD(생성, 읽기, 업데이트, 삭제) 작업을 수행하는 Repository 인터페이스를 정의함
public interface PostRepository extends JpaRepository<Post, Long> {

}
/*
    PostRepository 인터페이스는 JpaRepository를 상속받아 Post 엔티티에 대한 데이터베이스 작업을 처리하는 레포지토리 역할을 함
    JpaRepository를 상속함으로써, PostRepository는 기본적인 CRUD 메서드를 자동으로 제공하며 다음과 같은 메서드들이 포함됨
        save(Post entity): 엔티티를 저장하거나 업데이트
        findById(Long id): ID로 엔티티를 조회
        findAll(): 모든 엔티티를 조회
        deleteById(Long id): ID로 엔티티를 삭제

    이외에도 추가적인 메서드를 정의할 수 있으며 예를 들어, 게시물 제목이나 작성자에 따라 게시물을 검색하는 메서드를 추가할 수 있음
*/