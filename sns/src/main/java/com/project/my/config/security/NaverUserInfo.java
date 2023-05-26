package com.project.my.config.security;

import java.util.Map;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NaverUserInfo implements OAuth2UserInfo {
    private Map<String, Object> attributes;
    // 네이버 이메일 리턴
    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }
    // 네이버 이름 리턴
    @Override
    public String getName() {
        return (String) attributes.get("name");
    }
    // 네이버 닉네임 리턴
    @Override
    public String getNickName() {
        return (String) attributes.get("nickname");
    }
    // 로그인 타입 리턴
    @Override
    public String getProvider() {
        return "naver";
    }
    // 고유 id 리턴
    @Override
    public String getProviderId() {
        return (String) attributes.get("id");
    }
    // 성별 리턴
    @Override
    public String getGender() {
        String gender = (String) attributes.get("gender");
        if(gender.equals("M")){
            return "남성";
        } else{
            return "여성";
        }              
    }
    // 휴대폰 번호 리턴
    @Override
    public String getMobile() {
        String mobile = (String) attributes.get("mobile");
        mobile = mobile.replaceAll("-", "");
        return mobile;      
    }   
}
