package com.example.demo.user.service;

import com.example.demo.post.domain.Post;
import com.example.demo.post.repository.PostRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.dto.UserMyPageDTO;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserMyPageDTO> getMyPage() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserMyPageDTO::new)
                .collect(Collectors.toList());
    }
}
