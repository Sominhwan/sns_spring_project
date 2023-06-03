package com.project.my.module.userRole.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.project.my.module.sns.dto.SMSDTO;
import com.project.my.module.userRole.entity.PostEntity;
import com.project.my.module.userRole.entity.SMSEntity;
import com.project.my.module.userRole.entity.UserInfoEntity;

@Mapper
@Repository
public interface UserRepository {
    String UserInfoEntity = null;
    // 이메일 중복 체크
    int userEmailChk(String userEmail);
    // 휴대폰 중복 체크
    int userPhoneNumChk(String userPhoneNum);
    // 일반 회원가입 INSERT
    void insertMember(UserInfoEntity userInfoEntity);
    // 소셜 회원가입 INSERT
    void insertSocialMember(UserInfoEntity userInfoEntity);
    // 이메일 해쉬값으로 이메일 값 받아오기
    String getUserEmail(String code);
    // 이메일 검증하기
    void setEmailcertification(String userEmail);
    // 폼에 입력받은 아이디(이메일)를 통해 해당 유저 정보 받아오기
    UserInfoEntity findByUserId(String userEmail);
    // 폼에 입력받은 아이디(소셜 아이디)를 통해 해당 유저 정보 받아오기
    Optional<UserInfoEntity> findByLoginId(String provideId);
    // 회원 아이디 찾기(아이디, 가입일자) 받아오기
    UserInfoEntity findUserId(@Param("userName") String userName, @Param("userNickName") String userNickName);
    // 회원 비밀번호 찾기(비밀번호, 유저타입) 받아고기
    UserInfoEntity findUserPwd(@Param("userEmail") String userEmail);
    // 회원 비밀번호 변경하기 UPDATE
    void updateUserPassword(@Param("userEmail") String userEmail, @Param("changePassword") String changePassword);
    
    /************************************* 관리자 기능 ***********************************************************/
    // 회원이름을 통해 회원정보 검색
    List<UserInfoEntity> search(String userName);
    // 회원 이메일을 통해 userinfo 삭제
    void deleteUserInfo(@Param("userEmail") String userEmail);
    // 회원이메일을 통해 회원 게시물 검색
    List<PostEntity> searchPost(String userEmail);
    // PostId를 통해 post 삭제
    void deleteUserPost(@Param("postId") String postId);
    // 회원이메일을 통해 회원이메일주소 검색
    ArrayList<UserInfoEntity> searchUserEmail(String userEmail);    
    // 광고수신 회원 휴대폰 번호 SELECT
    List<SMSDTO> selectUserPhoneList();
    // 카페24 SMS 전송 데이터 INSERT
    void insertSMSData(@Param("setPhone") String setPhone, @Param("setContent") String setContent);
    // 카페24 SMS 전송 데이터 SELECT
    List<SMSEntity> selectSmsData();   
}
