package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    /**
     * localhost:8080/articles/new GET 요청시
     * @return new.mustache 반환
     */
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    /**
     * localhost:8080/articles/create POST 요청시 (Form 입력값)
     * 폼 데이터를 전송받아 DTO 객체에 담은 후 데이터 베이스에 저장한다.
     * @return
     */
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString()); //폼으로 부터 전달된 데이터
        //Todo
        //1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        System.out.println(article.toString()); //DTO 엔티티 값 확인
        //2. 리파지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString()); //리포지토리 값 확인
        return "";
    }
}
