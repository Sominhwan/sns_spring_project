package com.project.my.module.sns.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.my.module.sns.dto.AuthDTO;
import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.UserRepository;
import com.project.my.util.GmailService;
import com.project.my.util.SHA256;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false) // readOnly = true 경우 db 접근시 select 만 가능
public class AuthServiceApiV1 {
    private final UserRepository userRepository;
    private final GmailService gmailService;
    private final PasswordEncoder passwordEncoder;

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
}
