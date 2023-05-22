package com.project.my.module.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class QuestController {
    
    @GetMapping("/")
    public String index(Model model) {
        // 필요한 데이터를 모델에 담아 뷰 템플릿으로 전달
        model.addAttribute("imageUrls", new String[] {
                "images/20230311_210102.jpg",
                "images/20230311_210726.jpg",
                "images/20230311_211207.jpg",
                "images/20230311_211718.jpg",
                "images/20230311_212519.jpg",
                "images/20230311_212734.jpg",
                "images/20230426_203407.jpg"
        });

        return "index";
    }
}
