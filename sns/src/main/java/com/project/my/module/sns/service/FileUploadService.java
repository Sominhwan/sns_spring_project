package com.project.my.module.sns.service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.my.module.userRole.entity.FriendmanagerEntity;
import com.project.my.module.userRole.entity.GuestBookEntity;
import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.FileUploadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final FileUploadRepository fileUploadRepository; 
    
     // 프로필사진 변경
    public ArrayList<GuestBookEntity> findBgImage(String userEmail){
        ArrayList<GuestBookEntity> profileList = fileUploadRepository.findByUserId(userEmail);
        String userImage = fileUploadRepository.getProfileImage(userEmail);
        for(GuestBookEntity guestBookEntity : profileList){
            guestBookEntity.setUserImage(userImage);
        }
        return profileList;
    }
    
    // 방명록 업데이트
    public void updateGuestBook(String gbComment, String userEmail){
        fileUploadRepository.updateGuestBook(gbComment, userEmail);
        // System.out.println("범인아님");
        // return "방명록 업데이트";
    }

    // 방명록 최초추가
    public void insertGuestBook(String gbComment, String userEmail){
        fileUploadRepository.insertGuestBook(gbComment, userEmail);
    }

    // 친구목록 불러오기
    public ArrayList<FriendmanagerEntity> getFriendList(String userEmail){
        ArrayList<FriendmanagerEntity> friendList = fileUploadRepository.getFriendList(userEmail);
        for(FriendmanagerEntity friendmanagerEntity : friendList){
            String friendEmail = friendmanagerEntity.getFriendEmail();
            String friendImage = fileUploadRepository.getProfileImage(friendEmail);
            String friendName = fileUploadRepository.findByUserName(friendEmail);
            friendmanagerEntity.setUserImage(friendImage);
            friendmanagerEntity.setFriendName(friendName);
        }
        return friendList;
    }

    // 친구정보 불러오기
    public ArrayList<UserInfoEntity> getFriendInfo(String userEmail){
        ArrayList<UserInfoEntity> getFriendInfo = fileUploadRepository.getFriendInfo(userEmail);
        return getFriendInfo;
    }
    
}
