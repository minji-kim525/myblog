package com.sparta.test1.controller;

import com.sparta.test1.dto.SignupRequestDto;
import com.sparta.test1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserService userService;
//    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
//        this.kakaoUserService=kakaoUserService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }


    // 회원 관련 정보 받기
    // @PostMapping("/user/userinfo")
//    @ResponseBody
//    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        String username = userDetails.getUser().getUsername();
//        UserRoleEnum role = userDetails.getUser().getRole();
//        boolean isAdmin = (role == UserRoleEnum.ADMIN);
//
//        return new UserInfoDto(username, isAdmin);
//    }

//    @GetMapping("/user/kakao/callback")
//    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
//        kakaoUserService.kakaoLogin(code);
//        return "redirect:/";
//    }
}