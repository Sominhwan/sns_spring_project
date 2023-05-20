package com.project.my.module.sns.service;

import org.springframework.stereotype.Service;

import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;
    // 폼에 입력받은 아이디(이메일) 을 통해 해당 회원정보 찾기
    public UserInfoEntity findById(String userEmail){
        return userRepository.findByUserId(userEmail);
    }
}
