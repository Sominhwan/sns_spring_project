package com.project.my.module.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

/* 환경설정(회원탈퇴, 회원정보 수정) 페이지 컨트롤러 */
@Controller
@RequiredArgsConstructor
public class ConfigSettingPageController {
    //회원삭제 페이지 이동
    @GetMapping("/userdelete")
    public String userdelete() {
        return "/privacy/delete";
    }
    //회원 삭제시 이메일 인증으로 이동
    @GetMapping("/userEmail")
    public String userEmail() {
        return "/privacy/email";
    }
    //회원 정보 페이지이동
    @GetMapping("/userUpdate")
    public String userUpdate() {
        return "/privacy/update";
    }
    //회원 정보 변경 페이지 이동
    @GetMapping("/userUpdate1")
    public String userUpdate1() {
        return "/privacy/update1";
    }
    //도움말 페이지 이동
    @GetMapping("/userHelp")
    public String userHelp() {
        return "/help/help";
    }
    @GetMapping("/search")
    public String search() {
        return "/privacy/search";
    }

    //update페이지로 이동하는 버튼
    @GetMapping("/ToUpdate")
    public String ToUpdate() {
        return "redirect:/userUpdate";
    }
    //delete페이지로 이동하는 버튼
    @GetMapping("/ToDelete")
    public String ToDelete() {
        return "redirect:/userDelete";
    }
    //Update1페이지로 이동하는 버튼
    @GetMapping("/ToUpdate1")
    public String ToUpdate1() {
        return "redirect:/userUpdate1";
    }
    @GetMapping("/ToHelp")
    public String ToHelp() {
        return "redirect:/userHelp";
    }




}
