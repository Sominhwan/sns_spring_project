package com.project.my.module.sns.controller;

import java.util.HashMap;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.my.module.userRole.entity.UserInfoEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
    
    @PostMapping("/loginOk.action")
    @ResponseBody 
    public HashMap<String, String> loginOkPage(@AuthenticationPrincipal User user){
        System.out.println("로그인 테스트중");
        HashMap<String, String> map = new HashMap<String,String>();
        map.put("userEmail", user.getUsername());
        map.put("userProfile", "이미지넣기");
        return map;
    }
    

    @PostMapping("/signUpInfoCheck")
    @ResponseBody 
    public String initRequest(@RequestParam("userEmail") String userEmail, @RequestParam("userName") String userName, @RequestParam("gender") String gender,
    @RequestParam("userNickName") String userNickName, @RequestParam("userPhoneNum") String userPhoneNum, @RequestParam("password") String password, @RequestParam("agreement") String agreement) {       
        System.out.println(userEmail);
        System.out.println(userName);
        System.out.println(gender);
        System.out.println(userNickName);
        System.out.println(userPhoneNum);
        System.out.println(password);
        System.out.println(agreement);
        return "하이";
    }     
}
