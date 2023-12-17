package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signUpPage() {
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm) {
        log.info(memberForm.toString());

        Member member = memberForm.toEntity();
        log.info(member.toString());

        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        return "redirect:/members/" + saved.getId();
    }

    /**
     * localhost:8080/members/{id} GET 요청시 데이터 조회 요청
     * @param id Atricle의 id
     * @return
     */
    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id,
                       Model model) {
        log.info("id= " +id);
        //1. id를 조회해 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        //2. 모델에 데이터 등록하기
        model.addAttribute("member", memberEntity);
        //3. 뷰 페이지 반환하기
        return "members/show";
    }

    /**
     * localhost:8080/members GET 요청시 데이터 목록 조회하기
     * @return
     */
    @GetMapping("/members")
    public String index(Model model) {
        //1. 모든 데이터 가져오기
        ArrayList<Member> memberEntityList = memberRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("memberList", memberEntityList);
        //3. 뷰 페이지 설정하기
        return "members/index";
    }

    /**
     * localhost:8080/members/{id} GET 요청시 수정 페이지로
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        //수정할 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("member", memberEntity);
        //뷰 페이지 설정하기
        return "members/edit";
    }

    /**
     * localhost:8080/members/update POST  요청시 데이터 수정하기
     * @param form
     * @return
     */
    @PostMapping("/members/update")
    public String update(MemberForm form) {
        //1. DTO를 엔티티로 변환하기
        Member memberEntity = form.toEntity();
        log.info(memberEntity.toString());
        //2. 엔티티를 DB에 저장하기
        //2-1. DB에서 기존 데이터 가져오기
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        //2-2. 기존 데이터 값을 갱신하기
        if(target != null) {
            memberRepository.save(memberEntity);
        }
        //3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/members/" + memberEntity.getId();
    }
}
