package com.project.my.module.sns.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/signUpInfo")
    public String signUpInfo(HttpServletRequest request, ModelMap model) throws Exception {    
        String[] arr = request.getParameterValues("agreement");    
        model.addAttribute("arr", arr);
        return "auth/signUpInfo";
    }
}
