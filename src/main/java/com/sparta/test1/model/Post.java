package com.sparta.test1.model;

import com.sparta.test1.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Post extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long userId;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String name;




    public Post(PostRequestDto requestDto,Long userId) {

        this.userId=userId;
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.name=requestDto.getName();

    }

    public void update(PostRequestDto requestDto){
        this.title= requestDto.getTitle();
        this.contents= requestDto.getContents();

    }
}