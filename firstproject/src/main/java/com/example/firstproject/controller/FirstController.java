package com.example.firstproject.controller;

//컨트롤러 선언과 동시에 자동으로 임포트 설정 -IDE에서 제공하는 Auto Import 기능 사용
import org.springframework.stereotype.Controller;
//URL 연결 요청과 동시에 자동으로 임포트
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    /**
     * URL: localhost:8080/hi GET 요청시
     * @return resource/template/greetings.mustache 을 반환함
     */
    @GetMapping("/hi")
    public String niceToMeetYou() {
        return "greetings";
    }
}
