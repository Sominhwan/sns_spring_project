package com.project.my.module.userRole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendmanagerEntity {
	private int friendIndex;
	private String userEmail;
	private String friendEmail;
	private int friendSign;

	public void setFriendIndex(int friendIndex) {
		this.friendIndex = friendIndex;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setFriendEmail(String friendEmail) {
		this.friendEmail = friendEmail;
	}

	public void setFriendSign(int friendSign) {
		this.friendSign = friendSign;
	}

}
