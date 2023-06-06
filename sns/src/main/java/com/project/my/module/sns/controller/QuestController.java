package com.project.my.module.sns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class QuestController {
    
    @GetMapping("/quest")
    public String index(Model model) {
        // 필요한 데이터를 모델에 담아 뷰 템플릿으로 전달
        model.addAttribute("imageUrls", new String[] {
                "images/image1.jpg",
                "images/image2.jpg",
                "images/image3.jpg",
                "images/image4.jpg",
                "images/image5.jpg",
                "images/image6.jpg",
                "images/image7.jpg",
                "images/image8.jpg",
                "images/image9.jpg",
                "images/image10.jpg",
                "images/image11.jpg",
                "images/image12.jpg",
                "images/image13.jpg",
                "images/image14.jpg",
                "images/image15.jpg",
                "images/image16.jpg",
                "images/image17.jpg",
                "images/image18.jpg",
                
        });

        return "quest/quest";
    }
}
