package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * 입력 폼에서 제목과 내용을 받아오는 DTO 객체
 */
@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title; //제목을 받을 필드
    private String content; //내용을 받을 필드

    public Article toEntity() {
        return new Article(null, title, content);
    }
}
