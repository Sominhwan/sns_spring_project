package com.project.my.module.sns.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import com.project.my.module.userRole.entity.CommentEntity;
import com.project.my.module.userRole.entity.FriendmanagerEntity;
import com.project.my.module.userRole.entity.PostEntity;
import com.project.my.module.userRole.entity.PostlikeEntity;
import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.CommentRepository;
import com.project.my.module.userRole.repository.FriendRepository;
import com.project.my.module.userRole.repository.PostRepository;
import com.project.my.module.userRole.repository.PostlikeRepository;
import com.project.my.module.userRole.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostlikeRepository postlikeRepository;
    
    public List<FriendmanagerEntity> followconsent(String userEmail){
        return friendRepository.followconsent(userEmail);
    }

    
    public List<UserInfoEntity> followuser(String userEmail){
        return userRepository.followuser(userEmail);
    }
    public List<UserInfoEntity> getListPMember2(String userEmail) {
        return userRepository.getListPMember2(userEmail);
    }
}
