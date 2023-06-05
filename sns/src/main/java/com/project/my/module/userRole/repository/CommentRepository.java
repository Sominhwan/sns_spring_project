package com.project.my.module.userRole.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.project.my.module.userRole.entity.CommentEntity;

@Repository
@Mapper
public interface CommentRepository {
    String CommentEntity=null;

    List<CommentEntity> listPReply(@Param("postId") int postId);
    

    List<CommentEntity> commentAdd(@Param("userEmail") String userEmail, @Param("commentDetail") String commentDetail, @Param("postId") int postId);
    void commentdel(@Param("commentId") int commentId);
    void commentupdate(@Param("commentId") int commentId, @Param("commentDetail") String commentDetail);
    
    List<CommentEntity> insertReply(@Param("postId") int postId, @Param("userEmail") String userEmail, @Param("commentDetail") String commentDetail, @Param("commentParrent") int commentParrent);

    CommentEntity replycheck(@Param("commentId") int commentId);
}
