package com.project.my.module.userRole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostimageEntity {
	private int imageId;
	private String userEmail;
	private String imageName;
	private String videoName;
	private int imageSize;
	private int videoSize;

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public void setImageSize(int imageSize) {
		this.imageSize = imageSize;
	}

	public void setVideoSize(int videoSize) {
		this.videoSize = videoSize;
	}

}
