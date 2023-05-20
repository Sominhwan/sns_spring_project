package com.project.my.module.sns.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.my.config.security.PrincipalDetails;
import com.project.my.module.sns.service.MemberValidateService;

import lombok.RequiredArgsConstructor;

@RestController // 비돋기 데이터 처리후 데이터 반환
@RequiredArgsConstructor
public class AuthController {
    private final MemberValidateService memberValidateService;

    // 로그인 성공여부 
    @GetMapping("/loginOk.action")
    @ResponseBody 
    public HashMap<String, String> loginOkPage(@AuthenticationPrincipal PrincipalDetails principalDetails){
        HashMap<String, String> map = new HashMap<String,String>();
        map.put("userEmail", principalDetails.getUsername()); // 아이디 반환
        map.put("userRole", principalDetails.getAuthorities().toString());
        return map;
    }
    // 회원가입 성공 여부
    @PostMapping("/signUpInfoCheck")
    @ResponseBody 
    public Map memberJoin(@RequestParam("userEmail") String userEmail, @RequestParam("userName") String userName, @RequestParam("gender") String gender,
    @RequestParam("userNickName") String userNickName, @RequestParam("userPhoneNum") String userPhoneNum, @RequestParam("password") String password, @RequestParam("agreement") String agreement) {       
        HashMap<String, String> map = new HashMap<String,String>();
        Map result = new HashMap<String, Object>();
        map.put("userEmail", userEmail);
        map.put("userName", userName);
        map.put("gender", gender);
        map.put("userNickName", userNickName);
        map.put("userPhoneNum", userPhoneNum);
        map.put("password", password);
        map.put("agreement", agreement);
        
        result = memberValidateService.memberValidation(map);
        return result;
    }     
}
