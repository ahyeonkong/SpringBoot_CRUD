package com.example.demo.user.controller;

import com.example.demo.user.dto.UserMyPageDTO;
import com.example.demo.user.service.UserService;
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
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/my-page")
    public ResponseEntity<List<UserMyPageDTO>> getMyPage(){
        List<UserMyPageDTO> myPage = userService.getMyPage();
        return ResponseEntity.ok(myPage);
    }
}
