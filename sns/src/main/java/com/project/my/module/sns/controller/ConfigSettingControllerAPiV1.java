package com.project.my.module.sns.controller;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.my.module.sns.service.ConfigSettingServiceApiV1;

import lombok.RequiredArgsConstructor;

/* 환경설정(회원탈퇴, 회원정보 수정) 비동기 처리 컨트롤러 */
@RestController // 비돋기 데이터 처리후 데이터 반환
@RequiredArgsConstructor
public class ConfigSettingControllerAPiV1 {
    private final ConfigSettingServiceApiV1 configSettingServiceApiV1;
    @GetMapping("/getUniversityData")
    @ResponseBody 
    public Map getUniversityData(@Param("university") String university) {       
        return  configSettingServiceApiV1.getUniversityData(university);
    }          
}
