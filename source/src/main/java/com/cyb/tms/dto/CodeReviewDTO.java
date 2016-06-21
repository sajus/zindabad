package com.cyb.tms.dto;

import java.util.Date;

import com.cyb.tms.dto.base.BaseDTO;

public class CodeReviewDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2805315708370963378L;
	private Long Id;
	private String jiraId;
	private Long tmsUsersByDeveloper;    
	private Long tmsUsersByFixedBy;  
	private Long reviewer;   
	private String commentType;
	private String comments;
	private Date commentsFixedDate;
	private String fileName;
	private String pullRequest;
	private Date reviewDate;
	private String reviewerType;
	
	public CodeReviewDTO() {
		super();
	}

	public CodeReviewDTO(Long id, String jiraId, Long tmsUsersByDeveloper, Long tmsUsersByFixedBy, Long reviewer,
			String commentType, String comments, Date commentsFixedDate, String fileName, String pullRequest,
			Date reviewDate, String reviewerType) {
		super();
		Id = id;
		this.jiraId = jiraId;
		this.tmsUsersByDeveloper = tmsUsersByDeveloper;
		this.tmsUsersByFixedBy = tmsUsersByFixedBy;
		this.reviewer = reviewer;
		this.commentType = commentType;
		this.comments = comments;
		this.commentsFixedDate = commentsFixedDate;
		this.fileName = fileName;
		this.pullRequest = pullRequest;
		this.reviewDate = reviewDate;
		this.reviewerType = reviewerType;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getJiraId() {
		return jiraId;
	}

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	public Long getTmsUsersByDeveloper() {
		return tmsUsersByDeveloper;
	}

	public void setTmsUsersByDeveloper(Long tmsUsersByDeveloper) {
		this.tmsUsersByDeveloper = tmsUsersByDeveloper;
	}

	public Long getTmsUsersByFixedBy() {
		return tmsUsersByFixedBy;
	}

	public void setTmsUsersByFixedBy(Long tmsUsersByFixedBy) {
		this.tmsUsersByFixedBy = tmsUsersByFixedBy;
	}

	public Long getReviewer() {
		return reviewer;
	}

	public void setReviewer(Long reviewer) {
		this.reviewer = reviewer;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCommentsFixedDate() {
		return commentsFixedDate;
	}

	public void setCommentsFixedDate(Date commentsFixedDate) {
		this.commentsFixedDate = commentsFixedDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPullRequest() {
		return pullRequest;
	}

	public void setPullRequest(String pullRequest) {
		this.pullRequest = pullRequest;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewerType() {
		return reviewerType;
	}

	public void setReviewerType(String reviewerType) {
		this.reviewerType = reviewerType;
	}
	
	
	
	
	
}
