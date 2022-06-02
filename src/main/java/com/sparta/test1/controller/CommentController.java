package com.sparta.test1.controller;

import com.sparta.test1.dto.CommentRequestDto;

import com.sparta.test1.model.Comment;
import com.sparta.test1.security.UserDetailsImpl;
import com.sparta.test1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    //댓글 생성
    @PostMapping("/api/comments")
    public Map<String,Object> createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails ==null){
            throw new IllegalArgumentException("로그인이 필요한 기능입니다.");
        }
        Long userId=userDetails.getUser().getId();
        Comment comment = commentService.createComment(commentRequestDto, userId);
        Map<String,Object>list=new HashMap<>();
        list.put("id",comment.getUserId());
        list.put("username",comment.getNickname());
        list.put("comment",comment.getComment());
        list.put("creatAt",comment.getCreateAt());

        return list;
    }

//    //댓글 조회
////    @GetMapping("/api/posts/comments")
////    public List<Post> getALlPost(){
////
////        return postService.getAllPost();
//////        ArrayList<AllDto>list=new ArrayList<>();
////    }
//
//
//    // 댓글 삭제(자신만 가능하게)
//    @DeleteMapping("/api/comments/{id}")
//    public Long deleteComment(@PathVariable Long id){
//        commentService.deleteComment(id);
//        return id;
//    }
//    // 댓글 수정
//    @PostMapping("/api/comments/{id}")
//    public Map<String, Object> updatPost(@PathVariable Long id,@RequestBody CommentRequestDto commentRequestDto){
//        Map<String, Object> map = new HashMap<>();;
//        Comment comment=commentService.updateComment(id,commentRequestDto);
//        map.put("comment",comment);
//        return map;
//    }
//
}
