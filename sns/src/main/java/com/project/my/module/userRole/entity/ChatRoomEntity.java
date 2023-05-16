package com.project.my.module.userRole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomEntity {
	private int roomId;
	private String userEmail;
	private String createTime;
	private int lastCheck;

	public void setLastCheck(int lastCheck) {
		this.lastCheck = lastCheck;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
