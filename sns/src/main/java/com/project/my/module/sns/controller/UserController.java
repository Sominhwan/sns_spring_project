package com.project.my.module.sns.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.my.module.sns.service.UserInfoService;
import com.project.my.module.userRole.entity.UserInfoEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserInfoService userInfoService;

    @GetMapping("/user")
    public List<UserInfoEntity> userInfoEntities() {
        return userInfoService.getUserInfo();

    }
}