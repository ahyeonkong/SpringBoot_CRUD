package com.example.demo.user.domain;

import com.example.demo.post.domain.Post;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String name;
    @Column
    String img;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> postList = new ArrayList<>();
}
