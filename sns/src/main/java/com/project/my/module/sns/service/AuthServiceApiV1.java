package com.project.my.module.sns.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.my.module.sns.dto.AuthDTO;
import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.UserRepository;
import com.project.my.util.Gmail.GmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false) // readOnly = true 경우 db 접근시 select 만 가능
public class AuthServiceApiV1 {
    private final UserRepository userRepository;
    private final GmailService gmailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserInfoEntity userInfoEntity;

    // 유저 회원가입
    @Transactional
    public Map memberValidation(AuthDTO.ReqJoin reqDto){
        Map result = new HashMap<String, Object>();
     
        userRepository.insertMember(reqDto.toEntity(bCryptPasswordEncoder)); // 일반 회원가입 DB 접근

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
        userInfoEntity = userRepository.findUserId(userName, userNickName);

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
    // 유저 비밀번호 찾기
    @Transactional
    public Map getUserPwd(AuthDTO.ReqFindPwd reqDto){
        Map result = new HashMap<String, Object>();
        String userEmail = reqDto.getUserEmail();
        userInfoEntity = userRepository.findUserPwd(userEmail); // 암호화된 pwd 가져옴
        //passwordEncoder.matches(password, userPwd);
        if(userInfoEntity.getUserInfoType().equals("naver")){
            result.put("message", "네이버를 통해 찾으세요"); 
            return result;
        } else if(userInfoEntity.getUserInfoType().equals("kakao")){
            result.put("message", "카카오를 통해 찾으세요"); 
            return result;            
        } else if(userInfoEntity.getUserPwd() != null && userInfoEntity.getUserInfoType().equals("일반") ){ 
            result.put("success", "성공");  
            return result;           
        }

        result.put("fail", "실패"); 
        return result;          
    }  
    // 유저 비밀번호 변경하기
    @Transactional
    public Map changeUserPwd(AuthDTO.ReqChangePwd reqDto){
        Map result = new HashMap<String, Object>();
        String userEmail = reqDto.getUserEmail();
        String changePassword = reqDto.getUserPassword();

        userInfoEntity = userRepository.findUserPwd(userEmail); // 암호화된 pwd 가져옴
        String currentPassword = userInfoEntity.getUserPwd();

        boolean flag = bCryptPasswordEncoder.matches(changePassword, currentPassword);
        changePassword = bCryptPasswordEncoder.encode(changePassword); // 변경할 비밀번호 해쉬값 변경
        if(flag == true){
            result.put("fail", "실패"); 
            return result;
        } else {
            userRepository.updateUserPassword(userEmail, changePassword); // 비밀번호 변경           
            result.put("success", "성공"); 
            return result;       
        } 
    }  
    // 네브바 유저 프로필 가져오기
    @Transactional
    public List<UserInfoEntity> getUserProfile(String userNickName){
        List<UserInfoEntity> userProfileList = new ArrayList<>();
        userProfileList = userRepository.getUserProfileList(userNickName);
        return userProfileList;
    } 
    // 네브바 유저 이메일 가져오기
    @Transactional
    public String getUserProfileInputSearch(String userNickName){
        String userEmail = userRepository.getUserProfileInputSearch(userNickName);
        return userEmail;
    }          
    
}
