package com.project.my.module.userRole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostlikeEntity {
	private int postId;
	private String userEmail;
	private int likeNum;

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

}
