package com.project.my.module.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
// @RequiredArgsConstructor
public class FriendInfoController {
    @GetMapping("/profile")
    public String friendProfile(@RequestParam("userEmail") String userEmail, Model model) {
        model.addAttribute("userEmail", userEmail);
        return "/profile/profile";
    }
}
