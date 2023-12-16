package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

/**
 * 입력 폼에서 제목과 내용을 받아오는 DTO 객체
 */
public class ArticleForm {
    private String title; //제목을 받을 필드
    private String content; //내용을 받을 필드

    /**
     * DTO 생성자
     * @param title 제목
     * @param content 내용
     */
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    /**
     * 폼 데이터 값 확인을 위한 메서드
     */
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }


    public Article toEntity() {
        return new Article(null, title, content);
    }
}
