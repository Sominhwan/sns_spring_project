package com.project.my.module.userRole.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.project.my.module.userRole.entity.UserInfoEntity;

@Mapper
@Repository
public interface UserRepository {
    // 이메일 중복 체크
    int userEmailChk(String userEmail);
    // 휴대폰 중복 체크
    int userPhoneNumChk(String userPhoneNum);
    // 일반 회원가입 INSERT
    void insertMember(UserInfoEntity userInfoEntity);
    // 네이버 회원가입 INSERT
    // TODO
    // 카카오 회원가입 INSERT
    // TODO
    // 이메일 해쉬값으로 이메일 값 받아오기
    String getUserEmail(String code);
    // 이메일 검증하기
    void setEmailcertification(String userEmail);
    // 폼에 입력받은 아이디(이메일)를 통해 해당 유저 정보 받아오기
    UserInfoEntity findByUserId(String userEmail);
}
