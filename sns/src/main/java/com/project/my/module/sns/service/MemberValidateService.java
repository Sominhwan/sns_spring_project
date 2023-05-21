package com.project.my.module.sns.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false) // readOnly = true 경우 db 접근시 select 만 가능
public class MemberValidateService {
    private final UserRepository userRepository;
    private final GmailService gmailService;
    private final BCryptPasswordEncoder encoder;
    private String userEmail;
    private String userName;
    private String gender;
    private String userNickName;
    private String userPhoneNum;
    private String password;
    private String agreement;
    private int agree;

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
        if(agreement.equals("agreementOk")){ // 광고 수신여부 확인
            agree = 1;
        } else{
            agree = 0;
        }
        String encPassword = encoder.encode(password); // 해쉬값으로 인코딩된 비밀번호
        String userEmailHash = new SHA256().getSHA256(userEmail); // 이메일 SHA256 해쉬값 변경

        UserInfoEntity userInfoEntity = new UserInfoEntity(userName, gender, userNickName, userEmail, encPassword, userPhoneNum, userEmailHash, agree);
        userRepository.insertMember(userInfoEntity); // 일반 회원가입 DB 접근
            
        boolean checkMessage = gmailService.sendEmail(userEmail, userName);
        if(checkMessage){
            result.put("success", userEmail);
            return result;
        } else{
            result.put("error", "메일전송 실패");
            return result;
        }
    }
}
