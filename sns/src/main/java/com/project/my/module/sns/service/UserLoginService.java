package com.project.my.module.sns.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.UserLoginRepository;

@Service
public class UserLoginService {
    private final UserLoginRepository userLoginRepository;
    
    @Autowired
    public UserLoginService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }


    public UserInfoEntity findById(String userEmail){
        return userLoginRepository.findByUserId(userEmail);
    }

    // public Optional<UserInfoEntity> findOne(String userEmail) {
    //     return userLoginRepository.findByUserid(userEmail);
    // }   
}
