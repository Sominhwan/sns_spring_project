package com.project.my.module.sns.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.my.module.sns.service.FollowService;
import com.project.my.module.userRole.entity.FriendmanagerEntity;
import com.project.my.module.userRole.entity.UserInfoEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FollowControllerApiV1 {
    private final FollowService followService;

    @GetMapping("/followconsent")
    @ResponseBody
    public List<FriendmanagerEntity> followconsent(@RequestParam("userEmail") String userEmail) {
        return followService.followconsent(userEmail);
    }

    @GetMapping("/followuser")
    @ResponseBody
    public List<UserInfoEntity> followuser(@RequestParam("userEmail") String userEmail) {
        System.out.println(userEmail);
        return followService.followuser(userEmail);
    }
    
    @GetMapping("/getListPMember2")
    @ResponseBody
    public List<UserInfoEntity> getListPMember2(@RequestParam("userEmail") String userEmail) {
        return followService.getListPMember2(userEmail);
    }
}