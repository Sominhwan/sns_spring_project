package com.project.my.module.userRole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerEntity {
	private String mgEmail;
	private String pwd;

	public void setMgEmail(String mgEmail) {
		this.mgEmail = mgEmail;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
