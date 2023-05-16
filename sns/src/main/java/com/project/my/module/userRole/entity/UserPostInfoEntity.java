package com.project.my.module.userRole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPostInfoEntity {
	private String userEmail;
	private int userPostCount;

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserPostCount(int userPostCount) {
		this.userPostCount = userPostCount;
	}

}
