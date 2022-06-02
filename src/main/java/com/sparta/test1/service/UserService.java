package com.sparta.test1.service;

import com.sparta.test1.dto.SignupRequestDto;
import com.sparta.test1.model.User;
import com.sparta.test1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
//    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 닉네임, 비밀번호, 비밀번호 확인을 request에서 전달받기
    @Transactional
    public void registerUser(SignupRequestDto requestDto) {

        // 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
        String username= requestDto.getUsername();
        if(!username.matches("^[a-zA-Z0-9]{3,}$")){
            throw new IllegalArgumentException("닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성");
        }
        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`로 만들기
        if(requestDto.getPassword().contains(requestDto.getUsername())|requestDto.getPassword().length()<4){
            throw new IllegalArgumentException("회원가입 실패");
        }
        // 비밀번호 확인은 비밀번호와 정확하게 일치하기
        if(!requestDto.getPassword().equals(requestDto.getPasswordConfirm())){
            throw new IllegalArgumentException("회원가입 실패");
        }
        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        String email = requestDto.getEmail();

// 사용자 ROLE 확인
//        UserRoleEnum role = UserRoleEnum.USER;
//        if (requestDto.isAdmin()) {
//            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
//                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
//            }
//            role = UserRoleEnum.ADMIN;
//        }

        User user = new User(username, password, email);
        userRepository.save(user);
    }


}