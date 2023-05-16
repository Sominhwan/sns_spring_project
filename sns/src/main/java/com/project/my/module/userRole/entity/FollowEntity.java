package com.project.my.module.userRole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowEntity {
	private String userEmail;
	private int followNumber;

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setFollowNumber(int followNumber) {
		this.followNumber = followNumber;
	}

}
