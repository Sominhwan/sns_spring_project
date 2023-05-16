package com.project.my.module.sns.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImp implements UserInfoService {
    private final UserRepository userRepository;

    @Override
    public List<UserInfoEntity> getUserInfo() {
        return userRepository.getUserInfo();
    }
}
