package com.project.my.module.userRole.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CommentEntity {
	private int commentId;
	private int postId;
	private String userEmail;
	private String commentDetail;
	private String commentParrent;
	private String commentChild;
	private String commentDate;
	private String commentCorrect;

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setCommentDetail(String commentDetail) {
		this.commentDetail = commentDetail;
	}

	public void setCommentParrent(String commentParrent) {
		this.commentParrent = commentParrent;
	}

	public void setCommentChild(String commentChild) {
		this.commentChild = commentChild;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public void setCommentCorrect(String commentCorrect) {
		this.commentCorrect = commentCorrect;
	}

}
