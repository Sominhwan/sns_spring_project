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
    // 이메일 해쉬값으로 이메일 값 받아오기
    String getUserEmail(String code);
    // 이메일 검증하기
    void setEmailcertification(String userEmail);
}
