package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
//    REST API 구현
//    @Autowired
//    private ArticleRepository articleRepository;
//    //GET
//    /**
//     * 전체 목록 조회 API
//     * @return
//     */
//    @GetMapping("/api/articles")
//    public List<Article> index() {
//        return articleRepository.findAll();
//    }
//
//    /**
//     * 단일 게시글 조회 API
//     * @param id 게시글 id
//     * @return
//     */
//    @GetMapping("/api/articles/{id}")
//    public Article show (@PathVariable Long id) {
//        return articleRepository.findById(id).orElse(null);
//    }
//    //POST
//
//    /**
//     * 데이터 생성 API
//     * @param dto
//     * @return
//     */
//    @PostMapping("/api/articles")
//    public Article create (@RequestBody ArticleForm dto) {
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }
//    //PATCH
//
//    /**
//     * 데이터 전체 수정 API
//     * @param id 게시글 id
//     * @param dto 수정할 데이터
//     * @return
//     */
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update (@PathVariable Long id,
//                                          @RequestBody ArticleForm dto) {
//        //1. DTO ->  엔티티 변환하기
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//        //2. 타깃 조회하기
//        Article target = articleRepository.findById(id).orElse(null);
//        //3. 잘못된 요청 처리하기
//        if (target == null || id != article.getId()) {
//            //400, 잘못된 요청 응답!
//            log.info("잘못된 요청! id: {}, article: {}", id, article.getId());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        //4. 업데이트 및 정상 응답(200) 처리하기
//        target.patch(article); //일부 데이터만 삭제할 경우
//        Article updated = articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//    //DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        //1. 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//        //2. 잘못된 요청 처리하기
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        //3. 대상 삭제 처리하기
//        articleRepository.delete(target);
//        //return ResponseEntity.status(HttpStatus.OK).body(null);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

    //Service 계층을 추가한 구현
    @Autowired
    private ArticleService articleService;

    //GET
    /**
     * 전체 게시글 조회 API
     * @return
     */
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    /**
     * 단일 게시글 조회 API
     * @param id
     * @return
     */
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    //POST
    /**
     * 데이터 생성 API
     * @param dto
     * @return
     */
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create (@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH
    /**
     * 게시글 수정하기
     * @param id
     * @param dto
     * @return
     */
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update (@PathVariable Long id,
                                           @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE

    /**
     * 게시글 삭제 API
     * @param id
     * @return
     */
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete (@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Transaction Processing Test
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest (@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
