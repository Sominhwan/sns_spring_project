package com.project.my.module.userRole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {
	private int postId;
	private String userEmail;
	private int likeNum;
	private String imageName;
	private String videoName;
	private int shareNum;
	private int commentNum;
	private String creationDate;
	private int postReport;

	public void setPostReport(int postReport) {
		this.postReport = postReport;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public void setShareNum(int shareNum) {
		this.shareNum = shareNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

}
