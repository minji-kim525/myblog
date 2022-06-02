package com.sparta.test1.service;


import com.sparta.test1.dto.CommentRequestDto;
import com.sparta.test1.model.Comment;
import com.sparta.test1.model.Post;
import com.sparta.test1.repository.CommentRepository;
import com.sparta.test1.repository.PostRepository;
import com.sparta.test1.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

     //댓글 생성
    public Comment createComment(CommentRequestDto commentRequestDto, Long userId) {

        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );

        Comment comment = new Comment(userId,commentRequestDto.getNickname(), commentRequestDto.getComment(), post);

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(Long id, CommentRequestDto commentRequestDto) {

        Comment comment= commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
            comment.update(commentRequestDto);
            return comment;
    }

    public void deleteComment(Long id) {
        Post post=postRepository.findById(id).orElseThrow(null);
        commentRepository.deleteById(id);
    }

    //특정 포스트 댓글 모두 보기
    public List<Comment>getAllComment(Post post)
    {
        return commentRepository.findAllByPostOrderByModifiedAtDesc(post);
    }
    @Transactional
    public void deletePostComment(Long id){
        Post post=postRepository.findById(id).orElseThrow(null);
        commentRepository.deleteByPost(post);
    }
}
