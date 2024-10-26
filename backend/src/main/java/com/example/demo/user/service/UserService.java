package com.example.demo.user.service;

import com.example.demo.post.domain.Post;
import com.example.demo.post.repository.PostRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.dto.UserMyPageDTO;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<UserMyPageDTO> getMyPage() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> {
                    UserMyPageDTO dto = new UserMyPageDTO(user);
                    List<String> postTitles = postRepository.findByUser(user)
                            .stream()
                            .map(Post::getTitle)
                            .collect(Collectors.toList());
                    dto.setMyPostTitles(postTitles);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}