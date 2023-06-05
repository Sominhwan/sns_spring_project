package com.project.my.module.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

/* 메인(탐색, 프로필 등) 페이지 컨트롤러 */
@Controller
@RequiredArgsConstructor
public class MainPageController {
    // 메인 페이지 이동
    @GetMapping("/main")
    public String main() {
        return "/main/main";
    }   
    // 팔로우 페이지 이동
    @PostMapping("/follow")
    public String follow() {
        return "/follow/follow";
    } 
    // 탐색 페이지 이동
    @PostMapping("/quest")
    public String quest() {
        return "/quest/quest";
    } 
    // 프로필 페이지 이동
    @PostMapping("/profile")
    public String profile() {
        return "/profile/profile";
    }         
}
