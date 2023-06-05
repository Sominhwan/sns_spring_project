package com.project.my.module.userRole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestBookEntity {
	private String userEmail;
	private String gbComment;
	private String gbBackGroundImage;
	private String userImage;

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setGbComment(String gbComment) {
		this.gbComment = gbComment;
	}

	public void setGbBackGroundImage(String gbBackGroundImage) {
		this.gbBackGroundImage = gbBackGroundImage;
	}

	public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
