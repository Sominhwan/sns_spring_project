package com.project.my.module.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthPageController {
    @GetMapping("/index")
    public String index() {
        return "/auth/index";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "/auth/signUp";
    }

    @GetMapping("/termsService")
    public String termsService() {
        return "/auth/termsService";
    }    
}
