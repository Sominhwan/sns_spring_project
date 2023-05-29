package com.project.my.module.sns.service;

import org.springframework.stereotype.Service;

import com.project.my.module.userRole.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SHA256CheckService {
    private final UserRepository userRepository;

    public String getUserEmail(String code){
        String userEmail = userRepository.getUserEmail(code);
        if(userEmail != null){
            userRepository.setEmailcertification(userEmail);
        }
        return userEmail;
    }  
}
