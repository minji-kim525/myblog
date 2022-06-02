package com.sparta.test1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용 여부
// unique: 중복 허용 여부 (false 일때 중복 허용) default=false
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

//    @Column(unique = true)
//    private Long kakaoId;

    //일반 폼 로그인 회원들을 위한 생성자
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
//        this.kakaoId = null;
    }
//    public User(String username, String password, String email, Long kakaoId) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.kakaoId=kakaoId;
//    }

}