package com.project.my.module.sns.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.my.module.sns.service.AdminServiceAp1V1;

import lombok.RequiredArgsConstructor;

@RestController // 비동기기 데이터 처리후 데이터 반환
@RequiredArgsConstructor
public class AdminControllerApiV1 {
    private final AdminServiceAp1V1 adminServiceAp1V1;
    
    // 회원 아이디 찾기
    @PostMapping("/admin/UserSearch")
    @ResponseBody 
    public String UserSearch(@Param("userName") String userName) {      
        return adminServiceAp1V1.getUserInfo(userName);
    } 
    // 회원 데이터 삭제
    @PostMapping("/admin/UserInfoDelete")
    @ResponseBody 
    public void UserInfoDelete(@Param("userEmail") String userEmail, @Param("userEmailAll") String userEmailAll) {      
        // 회원데이터 하나만 삭제
        if(userEmail != null){

        }
        // 체크된 회원데이터 모두 삭제
        if(userEmailAll != null){

        }
    }     
}
