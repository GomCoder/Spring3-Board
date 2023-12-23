package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    @DisplayName("전체 게시글 목록 조회 테스트")
    void index() {
        //1. 예상 데이터
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        //2. 실제 데이터
        List<Article> articles = articleService.index();
        //3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    @DisplayName("단일 게시글 조회 테스트 성공")
    void show_success() {
        //1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        //2. 실제 데이터
        Article article = articleService.show(id);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @DisplayName("단일 게시글 조회 테스트 실패")
    void show_fail_does_not_exist_id_input() {
        //1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        //2. 실제 데이터
        Article article = articleService.show(id);
        //3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @DisplayName("새 게시글 생성 테스트 성공")
    @Transactional
    void create_success() {
        //1. 예상 데이터
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        //2. 실제 데이터
        Article article = articleService.create(dto);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @DisplayName("새 게시글 생성 테스트 실패")
    @Transactional
    void create_fail_dto_with_id_input() {
        //1. 예상 데이터
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        //2. 실제 데이터
        Article article = articleService.create(dto);
        //3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @DisplayName("Update를 성공한 경우 1")
    @Transactional
    void update_success_dto_with_existing_id_and_title_content() {
        //1. 예상 데이터
        Long id = 1L;
        String title = "바바바바";
        String content = "6666";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = articleService.update(id, dto);
        //2. 실제 데이터
        Article article = articleService.update(id, dto);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @DisplayName("Update를 성공한 경우 2")
    @Transactional
    void update_success_dto_with_existing_id_and_title() {
        //1. 예상 데이터
        Long id = 1L;
        String title = "바바바바";
        ArticleForm dto = new ArticleForm(id, title, null);
        Article expected = articleService.update(id, dto);
        //2. 실제 데이터
        Article article = articleService.update(id, dto);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @DisplayName("Update를 실패한 경우")
    @Transactional
    void update_fail_dto_input_for_id_that_do_not_exist() {
        //1. 예상 데이터
        Long id = -1L;
        String title = "바바바바";
        String content = "6666";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = articleService.update(id, dto);
        //2. 실제 데이터
        Article article = articleService.update(id, dto);
        //3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @DisplayName("Delete를 성공한 경우")
    @Transactional
    void delete_success_exist_id_input() {
        //1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        //2. 실제 데이터
        Article article = articleService.delete(id);
        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @DisplayName("Delete를 실패한 경우")
    @Transactional
    void delete_fail_not_exist_id_input() {
        //1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        //2. 실제 데이터
        Article article = articleService.delete(id);
        //3. 비교 및 검증
        assertEquals(expected, article);
    }
}
