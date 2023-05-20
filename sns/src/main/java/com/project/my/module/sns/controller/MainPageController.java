package com.project.my.module.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

/* 메인(탐색, 프로필 등) 페이지 컨트롤러 */
@Controller
@RequiredArgsConstructor
public class MainPageController {
    //메인 페이지 이동
    @PostMapping("/main")
    public String index() {
        return "/main/main";
    }   
}
