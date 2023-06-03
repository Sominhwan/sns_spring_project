package com.project.my.module.sns.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private int postId;
    private String userEmail;
    private int likeNum;
    private int shareNum;
    private int commentNum;
    private int postReport;

    public void setPostId(int postId) {
        this.postId = postId;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }
    public void setShareNum(int shareNum) {
        this.shareNum = shareNum;
    }
    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }
    public void setPostReport(int postReport) {
        this.postReport = postReport;
    } 
}
