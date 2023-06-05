package com.project.my.module.sns.dto;

import java.util.List;

import com.project.my.module.userRole.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MainDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResFriendManager {
        List<FriendManager> friendManagerList;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class UserInfo {
            private String userEmail;
            private String userNickName;
            private String userImage;
        }
        
        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class FriendManager {
            private UserInfo friendInfo;
            private int friendSign;
        }
    }


    @Data
    @Builder
    public static class commentAdd{
        private String userEmail;
        private String commentDetail;
        private int postId;

        public CommentEntity toEntity() {
            return CommentEntity.builder()
                    .userEmail(userEmail)
                    .commentDetail(commentDetail)
                    .postId(postId)
                    .build();         
        }
    }
}
