package com.project.my.module.userRole.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.my.module.userRole.entity.UserInfoEntity;

@Mapper
@Repository
public interface UserLoginRepository {
    UserInfoEntity findByUserId(String userEmail);
    //Optional<UserInfoEntity> findByUserid(String userEmail);
}
