package com.project.my.module.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    // 보낼메일함 페이지 이동
    @GetMapping("/admin/adminSentMailbox")
    public String sentMailbox() {    
        return "/admin/adminSentMailbox";
    }       
    // 통계 차트 페이지 이동
    @GetMapping("/admin/adminStatistics")
    public String adminStatistics() {    
        return "/admin/adminStatistics";
    }  
    // 해당하는 보낸 메일함 페이지 이동
    @GetMapping("/admin/adminSentMailbox/read")
    public ModelAndView read(@RequestParam("num") String num, ModelAndView mav) {
        mav.addObject("num", num);
        mav.setViewName("/admin/read");
        return mav;   
    }  
    
    // 메일 번역 팝업 페이지 전환
    @GetMapping("/admin/adminSentMailbox/popup/read")
    public ModelAndView popup(@RequestParam("num") String num, ModelAndView mav) {
        mav.addObject("num", num);
        mav.setViewName("/admin/translate");
        return mav;   
    }      
}
