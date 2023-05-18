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
}
