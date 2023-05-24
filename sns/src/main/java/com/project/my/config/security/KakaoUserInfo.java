package com.project.my.config.security;

import java.util.Map;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KakaoUserInfo implements OAuth2UserInfo{
    private Map<String, Object> attributes;
    // 카카오 이메일 리턴
    @Override
    public String getEmail() {
        return (String) ((Map) attributes.get("kakao_account")).get("email");
    }
    // 카카오 성별 리턴
    @Override
    public String getGender() {
        String gender = (String) ((Map) attributes.get("kakao_account")).get("gender");
        if(gender.equals("male")){
            gender = "남성";
        } else{
            gender = "여성";
        }
        return gender;
    }
    // 카카오 휴대폰 번호 리턴
    @Override
    public String getMobile() {
        return "-";
    }
    // 카카오 이름 리턴
    @Override
    public String getName() {
        return (String) ((Map) attributes.get("properties")).get("nickname");
    }
    // 카카오 닉네임 리턴
    @Override
    public String getNickName() {
        return (String) ((Map) attributes.get("properties")).get("nickname");
    }
    // 카카오 가입 타입 리턴
    @Override
    public String getProvider() {
        return "kakao";
    }
    // 카카오 id 리턴
    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }
    
}
