package com.project.my.module.sns.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.UserRepository;
import com.project.my.util.SHA256;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthDTO {
    // 회원가입 데이터 저장
    @Data
    @Builder
    public static class ReqJoin{
        private final UserRepository userRepository;
        private String userEmail;
        private String userName;
        private String gender;
        private String userNickName;
        private String userPhoneNum;
        private String password;
        private String agreement;
        private String userEmailHash;

        public UserInfoEntity toEntity(BCryptPasswordEncoder bCryptPasswordEncoder) {
        userEmailHash = new SHA256().getSHA256(userEmail); // 이메일 SHA256 해쉬값 변경
        return UserInfoEntity.builder()
                .userEmail(userEmail)
                .userGender(gender)
                .userName(userName)
                .userNickName(userNickName)
                .userPN(userPhoneNum)
                .userPwd(bCryptPasswordEncoder.encode(password))
                .userAd(agreement)
                .emailHash(userEmailHash)
                .build();
        }
    }  
    // 아이디 찾기 데이터 저장
    @Data
    @Builder
    public static class ReqFindId{
        private String userName;
        private String userNickName;

        public UserInfoEntity toEntity() {
            return UserInfoEntity.builder()
                    .userName(userName)
                    .userNickName(userNickName)
                    .build();         
        }
    }   
    // 비밀번호 찾기 데이터 저장
    @Data
    @Builder
    public static class ReqFindPwd{
        private String userEmail;

        public UserInfoEntity toEntity() {
            return UserInfoEntity.builder()
                    .userEmail(userEmail)
                    .build();         
        }
    }
    // 비밀번호 변경 데이터 저장
    @Data
    @Builder
    public static class ReqChangePwd{
        private String userEmail;
        private String userPassword;

        public UserInfoEntity toEntity() {
            return UserInfoEntity.builder()
                    .userEmail(userEmail)
                    .userPwd(userPassword)
                    .build();         
        }
    }               
}
