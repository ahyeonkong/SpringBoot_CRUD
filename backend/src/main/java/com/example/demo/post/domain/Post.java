package com.example.demo.post.domain;

import com.example.demo.user.domain.User;
import jakarta.persistence.*;

@Entity
/*
    @Entity는 JPA를 사용하여 Post 엔티티 정의
    즉, 데이터베이스의 테이블에 매핑될 클래스로 설정
*/
public class Post {
    @Id
    // @Id는 이 필드가 테이블의 기본 키임을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue는 기본 키의 값을 자동으로 생성하도록 설정. 각 레코드가 추가될 때마다 자동으로 ID가 증가함
    Long id;
    @Column
    // @Column은 이 필드가 테이블의 컬럼임을 나타냄
    String title;
    @Column
    String text;

    @ManyToOne(fetch = FetchType.LAZY)
    /*
        @ManyToOne은 다대일 관계임을 나타냄. (Post가 다, User가 1)
        fetch = FetchType.LAZY는 이 관계에서 User 데이터를 지연 로딩하도록 설정
        지연 로딩은 Post가 조회될 때 User를 바로 조회하지 않고, 나중에 필요할 때 조회하는 방식
    */
    @JoinColumn(name = "user_id")
    // @JoinColumn은 이 필드가 외래 키임을 나타냄
    User user;

    //setter 메서드
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
