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
	private int chatID;
	private String chatName;
	private String chatContent;
	private String chatTime;

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public void setchatID(int chatID) {
		this.chatID = chatID;
	}

	public void setchatName(String chatName) {
		this.chatName = chatName;
	}

	public void setchatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public void setchatTime(String chatTime) {
		this.chatTime = chatTime;
	}

}
