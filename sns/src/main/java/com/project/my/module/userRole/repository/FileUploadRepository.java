package com.project.my.module.userRole.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.project.my.module.userRole.entity.FriendmanagerEntity;
import com.project.my.module.userRole.entity.GuestBookEntity;
import com.project.my.module.userRole.entity.UserInfoEntity;

@Mapper
@Repository
public interface FileUploadRepository {
    // 프로필 사진 업데이트
    void updateUserImage(@Param("userImage") String userImage, @Param("userEmail")String userEmail);
    
    // 배경사진 최초추가
    void insertBackImage(@Param("userImage") String userImage, @Param("userEmail")String userEmail);

    // 배경사진 있는지 확인
    int selectBackImage(String userEmail);

    // 배경사진 업데이트
    void updateBackImage(@Param("backImage") String backImage, @Param("userEmail")String userEmail);

    // 배경사진,방명록 데이터 가져오기
    ArrayList<GuestBookEntity> findByUserId(String userEmail);
    
    // 유저프로필 가져오기
    String getProfileImage(String userEmail);

    // 방명록 업데이트
    void updateGuestBook(@Param("gbComment") String gbComment, @Param("userEmail") String userEmail);

    // 방명록 최초추가
    void insertGuestBook(@Param("gbComment") String gbComment, @Param("userEmail") String userEmail);

    // 친구목록 불러오기
    ArrayList<FriendmanagerEntity> getFriendList(String userEmail);

    // 친구이름 불러오기
    String findByUserName(@Param("userEmail") String userEmail);

    ArrayList<UserInfoEntity> getFriendInfo(String userEmail);
}
