package com.sparta.test1.service;


import com.sparta.test1.dto.PostRequestDto;
import com.sparta.test1.model.Comment;
import com.sparta.test1.model.Post;
import com.sparta.test1.model.User;
import com.sparta.test1.repository.PostRepository;
import com.sparta.test1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
//    private final UserRepository userRepository;
//    private final CommentService commentService;


    public Map<String,Object> createPost(PostRequestDto postRequestDto,Long userId) {
        Map<String,Object>list=new HashMap<>();
        if(postRequestDto.getContents().equals("")){
            throw new IllegalArgumentException("댓글 내용을 입력해주세요");
        }
        Post post=new Post(postRequestDto,userId);
        postRepository.save(post);
        list.put("post",post);
        return list;
    }
    public List<Post> getAllPost() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    public Map<String,Object> getDetail(Long id) {
        Post post=postRepository.findById(id).orElseThrow(
               () -> new NullPointerException("아이디가 존재하지 않습니다.")
       );
        Map<String,Object>getPostComment=new HashMap<>();
        getPostComment.put("title",post.getTitle());
        getPostComment.put("content",post.getContents());
        getPostComment.put("username",post.getName());
        getPostComment.put("modifiedAt",post.getModifiedAt());
//        List<Comment>comment=commentService.getAllComment(post);
//        getPostComment.put("comments",comment);
        return getPostComment;
    }

    public Long deletePost(Long id) {
        Post post=postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
{
            postRepository.deleteById(id);
        }
        return id;
    }

    @Transactional
    public Post update(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
            post.update(postRequestDto);
        return post;
    }

}

