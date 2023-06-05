package com.project.my.module.userRole.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.project.my.module.userRole.entity.PostlikeEntity;

@Mapper
@Repository
public interface PostlikeRepository {
    String PostlikeEntity =null;
    PostlikeEntity postLike(@Param("userEmail") String userEmail, @Param("postId") int postId);
    void heartup(@Param("postId") int postId, @Param("userEmail") String userEmail);
    void heartdel(@Param("postId") int postId, @Param("userEmail") String userEmail);
}
