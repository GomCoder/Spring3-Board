package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
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
     * @return localhost:8080/articles/ + 저장된 id
     */
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());
        //System.out.println(form.toString()); //폼으로 부터 전달된 데이터
        //Todo
        //1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString()); //DTO 엔티티 값 확인
        //2. 리파지토리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString()); //리포지토리 값 확인
        return "redirect:/articles/" + saved.getId();
    }

    /**
     * localhost:8080/articles/{id} GET 요청시 데이터 조회 요청
     * @param id 해당 atricle의 id
     * @return localhost:8080/articles/show
     */
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id,
                       Model model) {
        log.info("id= " +id);
        //1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        //3. 뷰 페이지 반환하기
        return "articles/show";
    }

    /**
     * localhost:8080/articles GET 요청시 데이터 목록 조회하기
     * @return localhost:8080/articles/index
     */
    @GetMapping("/articles")
    public String index(Model model) {
        //1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        //3. 뷰 페이지 설정하기
        return "articles/index";
    }

    /**
     * localhost:8080/articles/{id}/edit GET 요청시 데이터 수정 페이지로
     * @param id 해당 article의 id
     * @param model 저장할 model
     * @return localhost:8080/articles/{id}/edit
     */
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        //뷰 페이지 설정하기
        return "articles/edit";
    }

    /**
     * localhost:8080/articles/update POST 요청시 데이터 수정하기
     * @param form form 데이터
     * @return localhost:8080/articles/ + 해당 article의 id
     */
    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        //1. DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2. 엔티티를 DB에 저장하기
        //2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2. 기존 데이터 값을 갱신하기
        if(target != null) {
            articleRepository.save(articleEntity); //엔티티를 DB에 저장(갱신)
        }
        //3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    /**
     * localhost:8080/articles/delete GET 요청시 데이터 삭제하기
     * @return
     */
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다.");
        //1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상 엔티티 삭제하기
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!");
        }
        //3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }
}
