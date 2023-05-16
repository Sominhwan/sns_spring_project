package com.project.my.module.userRole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostshareEntity {
	private int postId;
	private String userEmail;
	private int shareNum;

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setShareNum(int shareNum) {
		this.shareNum = shareNum;
	}

}
