package com.example.demo.user.repository;

import com.example.demo.post.domain.Post;
import com.example.demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id); // 시퀀스 id로 유저 조회
}
