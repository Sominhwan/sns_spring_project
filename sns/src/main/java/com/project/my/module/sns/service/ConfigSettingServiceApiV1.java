package com.project.my.module.sns.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.my.module.userRole.entity.UniversityEntity;
import com.project.my.module.userRole.repository.UniversityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConfigSettingServiceApiV1 {
    private final UniversityRepository universityRepository;
    // 대학교 데이터 가져오기
    @Transactional
    public Map getUniversityData(String university){
        Map result = new HashMap<String, Object>();
        List<UniversityEntity> list = universityRepository.universityData(university);
        result.put("university", list);
        return result;
    }     
}
