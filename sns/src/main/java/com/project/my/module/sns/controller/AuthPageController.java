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

    @RequestMapping(value = "/signUpInfoCheck", method= RequestMethod.POST)
    public String initRequest(HttpServletRequest request, ModelMap model) throws Exception {
        request.setCharacterEncoding("UTF-8");
        String userEmail = request.getParameter("userEmail");
        String userName = request.getParameter("userName");
        String gender = request.getParameter("gender");
        String userNickName = request.getParameter("userNickName");
        String userPhoneNum = request.getParameter("userPhoneNum");
        String password = request.getParameter("password");
        String agreement = request.getParameter("agreement");
        
        System.out.println(userEmail);
        System.out.println(userName);
        System.out.println(gender);
        System.out.println(userNickName);
        System.out.println(userPhoneNum);
        System.out.println(password);
        System.out.println(agreement);
        //model.addAttribute("arr", arr);
        return "";
    }    

}
