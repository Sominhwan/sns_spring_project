package com.project.my.module.sns.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.UserRepository;
import com.project.my.util.SHA256;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthDTO {
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

        public UserInfoEntity toEntity(PasswordEncoder passwordEncoder) {
        userEmailHash = new SHA256().getSHA256(userEmail); // 이메일 SHA256 해쉬값 변경
        return UserInfoEntity.builder()
                .userEmail(userEmail)
                .userGender(gender)
                .userName(userName)
                .userNickName(userNickName)
                .userPN(userPhoneNum)
                .userPwd(passwordEncoder.encode(password))
                .userAd(agreement)
                .emailHash(userEmailHash)
                .build();
        }
    }  
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
}
