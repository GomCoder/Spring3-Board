package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    /**
     * URL: localhost:8080/hi GET 요청시
     * @param model model 객체를 받아오기
     * @return 받아온 model 객체를 greetings.mustache의 username 변수에 반영 후 반환
     */
    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "사용자");
        return "greetings";
    }

    /**
     * URL: localhost:8080/bye GET 요청시
     * @param model model 객체
     * @return 받아온 model 객체를 goodbye.mustache의 nickname 변수에 반영 후 반환
     */
    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "홍길동");
        return "goodbye";
    }
}
