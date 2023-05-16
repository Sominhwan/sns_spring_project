package com.project.my.module.userRole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SMSEntity {
	private String userPN;
	private String content;
	private String userRegTime;

	public void setUserPN(String userPN) {
		this.userPN = userPN;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUserRegTime(String userRegTime) {
		this.userRegTime = userRegTime;
	}
}
