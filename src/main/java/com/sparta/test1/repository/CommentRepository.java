package com.sparta.test1.repository;

import com.sparta.test1.model.Comment;
import com.sparta.test1.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<Comment> findAllByPostOrderByModifiedDtDesc(Post post);
//    void deleteByPost(Post post);
}
