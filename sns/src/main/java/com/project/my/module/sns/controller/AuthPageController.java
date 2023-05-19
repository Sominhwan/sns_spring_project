package com.project.my.module.sns.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.my.module.sns.service.SHA256CheckService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthPageController {
    private final SHA256CheckService sha256CheckService;
    // 로그인 페이지 이동
    @GetMapping("/index")
    public String index() {
        return "/auth/index";
    }
    // 회원가입 페이지 이동
    @GetMapping("/signUp")
    public String signUp() {
        return "/auth/signUp";
    }
    // 약관동의 페이지 이동
    @GetMapping("/termsService")
    public String termsService() {
        return "/auth/termsService";
    }    
    // 회원가입 및 정보 입력 페이지 이동
    @GetMapping("/signUpInfo")
    public String signUpInfo(HttpServletRequest request, ModelMap model) throws Exception {    
        String[] arr = request.getParameterValues("agreement");    
        model.addAttribute("arr", arr);
        return "auth/signUpInfo";
    }
    // 이메일 인증 확인 페이지 이동
    @GetMapping("/emailCheck")
    public String emailCheck() {
        return "/auth/emailCheck";
    }
    // 이메일 인증 확인 여부 판단 후 로그인 페이지 이동
    @GetMapping("/emailHashCheck")
    public ModelAndView emailHashCheck(@RequestParam("code") String code, ModelAndView mav) {       
        String userEmail = sha256CheckService.getUserEmail(code);
        if(userEmail != null){
            mav.addObject("emailCheck", "이메일 인증에 성공하였습니다.");
            mav.setViewName("/auth/emailCheckProc");          
            return mav;
        }
        mav.addObject("emailCheck", "이메일 인증에 실패하였습니다.");
        mav.setViewName("/auth/emailCheckProc");  
        return mav;
    }    
}
