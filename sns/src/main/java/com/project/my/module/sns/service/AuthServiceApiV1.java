package com.project.my.module.sns.service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.jni.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.my.module.sns.dto.AuthDTO;
import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.UserRepository;
import com.project.my.util.GmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false) // readOnly = true 경우 db 접근시 select 만 가능
public class AuthServiceApiV1 {
    private final UserRepository userRepository;
    private final GmailService gmailService;
    private final PasswordEncoder passwordEncoder;
    private SimpleDateFormat SDF_DATE = new SimpleDateFormat("yyyy'.'MM'.'dd");

    // 유저 회원가입
    @Transactional
    public Map memberValidation(AuthDTO.ReqJoin reqDto){
        Map result = new HashMap<String, Object>();
     
        userRepository.insertMember(reqDto.toEntity(passwordEncoder)); // 일반 회원가입 DB 접근

        boolean checkMessage = gmailService.sendEmail(reqDto.getUserEmail(), reqDto.getUserName());
        if(checkMessage){
            result.put("success", reqDto.getUserEmail());
            return result;
        } else{
            result.put("error", "메일전송 실패");
            return result;
        }
    } 
    // 유저 아이디 찾기
    @Transactional
    public Map getUserId(AuthDTO.ReqFindId reqDto){
        Map result = new HashMap<String, Object>();
        String userName = reqDto.getUserName();
        String userNickName = reqDto.getUserNickName();
        UserInfoEntity userInfoEntity = userRepository.findUserId(userName, userNickName);

        if(userInfoEntity.getUserInfoType().equals("naver")){
            result.put("message", "네이버를 통해 아이디를 찾으세요"); 
            return result;
        } else if(userInfoEntity.getUserInfoType().equals("kakao")){
            result.put("message", "카카오를 통해 아이디를 찾으세요"); 
            return result;            
        } else{
            String tempDate = userInfoEntity.getUserRegDate();
            tempDate = tempDate.replace("-", ".");
            result.put("userEmail", userInfoEntity.getUserEmail()); 
            result.put("userRegDate", tempDate); 
            return result;             
        }
    } 
}
