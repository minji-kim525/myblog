package com.sparta.test1.controller;


import com.sparta.test1.dto.PostRequestDto;
import com.sparta.test1.model.Post;
import com.sparta.test1.security.UserDetailsImpl;

import com.sparta.test1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;
//    private final CommentService commentService;


    //게시글 생성
    @PostMapping("/api/posts")
    public Map<String,Object> createPost(@RequestBody PostRequestDto postrequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails ==null){
            throw new IllegalArgumentException("로그인이 필요한 기능입니다.");
        }
        Long userId=userDetails.getUser().getId();
        return postService.createPost(postrequestDto,userId);
    }

    //전체 게시글 조회
    @GetMapping("/api/posts")
    public List<Post>getALlPost(){

        return postService.getAllPost();
//        ArrayList<AllDto>list=new ArrayList<>();
    }

    // 특정 게시글 조회(댓글도 보이도록)
    @GetMapping("/api/posts/{id}")
    public Map<String,Object> getDetail(@PathVariable Long id){

        return postService.getDetail(id);
    }

//    // 게시글 삭제(댓글까지)
//    @DeleteMapping("/api/posts/{id}")
//    public Long deletePost(@PathVariable Long id){
//        postService.deletePost(id);
//        commentService.deletePostComment(id);
//
//        return id;
//    }
    // 게시글 수정
    @PutMapping("/api/posts/{id}")
    public Long updatPost(@PathVariable Long id,
                          @RequestBody PostRequestDto postRequestDto ){

        Post post=postService.update(id,postRequestDto);
        return id;
    }

}

