package com.project.my.module.userRole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessagerEntity {
	private int roomId;
	private int chatMessagerId;
	private String userEmail;
	private String message;
	private String creationDate;

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public void setChatMessagerId(int chatMessagerId) {
		this.chatMessagerId = chatMessagerId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

}
