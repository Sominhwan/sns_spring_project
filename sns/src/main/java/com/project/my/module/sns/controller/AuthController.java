package com.project.my.module.sns.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.my.module.sns.service.MemberValidateService;
import com.project.my.module.sns.service.SHA256CheckService;
import com.project.my.module.userRole.entity.UserInfoEntity;

import lombok.RequiredArgsConstructor;

@RestController // 비돋기 데이터 처리후 데이터 반환
@RequiredArgsConstructor
public class AuthController {
    private final MemberValidateService memberValidateService;
    private final SHA256CheckService sha256CheckService;

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
        
        //MemberValidateService memberValidateService = new MemberValidateService();
        result = memberValidateService.memberValidation(map);
        return result;
    }     
}
