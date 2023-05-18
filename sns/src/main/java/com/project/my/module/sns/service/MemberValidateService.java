package com.project.my.module.sns.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.project.my.module.userRole.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberValidateService {
    private final UserRepository userRepository;
    private final GmailService gmailService;
    private String userEmail;
    private String userName;
    private String gender;
    private String userNickName;
    private String userPhoneNum;
    private String password;
    private String agreement;

    public Map memberValidation(HashMap<String, String> map){
        Map result = new HashMap<String, Object>();
        userEmail = (String)map.get("userEmail");
        userName = (String)map.get("userName");
        gender = (String)map.get("gender");
        userNickName = (String)map.get("userNickName");
        userPhoneNum = (String)map.get("userPhoneNum");
        password = (String)map.get("password");
        agreement = (String)map.get("agreement");
      
        if(userRepository.userEmailChk(userEmail) == 1){ // 이메일 중복 검사
            result.put("error", "이미 존재하는 이메일 입니다.");
            return result;
        }
        if(gender.equals("없음")){ // 성별 선택 유무 검사
            result.put("error", "성별을 선택해주세요.");
            return result;
        }
        if(userRepository.userPhoneNumChk(userPhoneNum) == 1){ // 휴대폰 중복 검사
            result.put("error", "이미 존재하는 휴대폰번호 입니다.");
            return result;
        }

        boolean checkMessage = gmailService.sendEmail(userEmail, userName);
        result.put("error", checkMessage);
        return result;
    }
}
