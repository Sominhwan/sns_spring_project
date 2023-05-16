package com.project.my.module.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProfilePageController {
    @GetMapping("/profile")
    public String profile() {
        return "/userPage/profile";
    }
}
