package com.sparta.test1.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PostRequestDto {
    private final String title;
    private final String name;
    private final String contents;
}
