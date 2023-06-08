package com.project.my.module.userRole.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.project.my.module.userRole.entity.PostEntity;

@Mapper
@Repository
public interface PostRepository {
    String PostEntity = null;

    List<PostEntity> plist();
    List<PostEntity> plist2(@Param("postId") int postId);
    List<PostEntity> plist3(@Param("userEmail") String userEmail); 
    
    void upcomment(@Param("postId") int postId);
    void minuscomment(@Param("postId") int postId);
    

    void upheart(@Param("postId") int postId);
    void downheart(@Param("postId") int postId);

    void insertPost(@Param("userEmail") String userEmail, @Param("imageName") String imageName);
    void insertVideo(@Param("userEmail") String userEmail, @Param("videoName") String videoName);
}
