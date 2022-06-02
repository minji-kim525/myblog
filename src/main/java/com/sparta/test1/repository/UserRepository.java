package com.sparta.test1.repository;

import com.sparta.test1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);//optional이 어떻게 사용되는 건지 알아보기
//    Optional<User>findByKakaoId(Long kakaoId);
}