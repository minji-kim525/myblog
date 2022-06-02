package com.sparta.test1.model;

import com.sparta.test1.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long userId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String comment;

    @ManyToOne//한개의 포스트에 여러개의 댓글을 달수있으므로
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    public Comment(Long userId,String nickname, String comment, Post post) {
        this.userId=userId;
        this.nickname = nickname;
        this.comment = comment;
        this.post = post;
    }

    public Comment(CommentRequestDto commentRequestDto) {
        this.nickname = commentRequestDto.getNickname();
        this.comment = commentRequestDto.getComment();
    }

    public void update(CommentRequestDto commentRequestDto) {

        this.comment = commentRequestDto.getComment();
    }

}
