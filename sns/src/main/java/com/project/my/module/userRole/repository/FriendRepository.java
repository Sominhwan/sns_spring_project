package com.project.my.module.userRole.repository;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.project.my.module.userRole.entity.FriendmanagerEntity;

@Mapper
@Repository
public interface FriendRepository {
    String FriendmanagerEntity = null;

    FriendmanagerEntity friendCheck(@Param("userEmail") String userEmail, @Param("friendEmail") String friendEmail);
    FriendmanagerEntity followfirend(@Param("userEmail") String userEmail, @Param("friendEmail") String friendEmail);
    FriendmanagerEntity followfirend2(@Param("userEmail") String userEmail, @Param("friendEmail") String friendEmail);
    FriendmanagerEntity delFriend(@Param("userEmail") String userEmail, @Param("friendEmail") String friendEmail);
    FriendmanagerEntity delFriend2(@Param("userEmail") String userEmail, @Param("friendEmail") String friendEmail);
}
