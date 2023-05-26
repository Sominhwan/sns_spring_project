package com.project.my.module.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminPageController {
    // 관리자 페이지 이동(회원 관리)
    @GetMapping("/admin/adminPage")
    public String adminPage() {    
        return "/admin/adminPage";
    }   
    // 게시물 관리 페이지 이동
    @GetMapping("/admin/adminPost")
    public String adminPost() {    
        return "/admin/adminPost";
    }  
    //  메일, SMS 페이지 이동
    @GetMapping("/admin/adminMail")
    public String adminMail() {    
        return "/admin/adminMail";
    }     
    // 통계 차트 페이지 이동
    @GetMapping("/admin/adminStatistics")
    public String adminStatistics() {    
        return "/admin/adminStatistics";
    }                        
}