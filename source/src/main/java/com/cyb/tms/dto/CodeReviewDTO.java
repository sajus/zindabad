package com.cyb.tms.dto;

import java.util.Date;

import com.cyb.tms.dto.base.BaseDTO;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.entity.TmsUsers;

public class CodeReviewDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2805315708370963378L;
	private Long reviewId;
	private String jiraId;
	private Long developerId;    
	private Long fixedById;  
	private Long reviewerId;   
	private String developerName;
	private String fixedByName;
	private String reviewerName;
	private String commentType;
	private String comments;
	private Date commentsFixedDate;
	private String fileName;
	private String pullRequest;
	private Date pullRequestDate;
	private Date reviewDate;
	private String reviewerType;
	
	public CodeReviewDTO() {
		super();
	}

	public CodeReviewDTO(Long reviewId, String jiraId, Long developerId, Long fixedById, Long reviewerId,
			String developerName, String fixedByName, String reviewerName, String commentType, String comments,
			Date commentsFixedDate, String fileName, String pullRequest, Date pullRequestDate, Date reviewDate,
			String reviewerType) {
		super();
		this.reviewId = reviewId;
		this.jiraId = jiraId;
		this.developerId = developerId;
		this.fixedById = fixedById;
		this.reviewerId = reviewerId;
		this.developerName = developerName;
		this.fixedByName = fixedByName;
		this.reviewerName = reviewerName;
		this.commentType = commentType;
		this.comments = comments;
		this.commentsFixedDate = commentsFixedDate;
		this.fileName = fileName;
		this.pullRequest = pullRequest;
		this.pullRequestDate = pullRequestDate;
		this.reviewDate = reviewDate;
		this.reviewerType = reviewerType;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getJiraId() {
		return jiraId;
	}

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	public Long getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(Long developerId) {
		this.developerId = developerId;
	}

	public Long getFixedById() {
		return fixedById;
	}

	public void setFixedById(Long fixedById) {
		this.fixedById = fixedById;
	}

	public Long getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(Long reviewerId) {
		this.reviewerId = reviewerId;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getFixedByName() {
		return fixedByName;
	}

	public void setFixedByName(String fixedByName) {
		this.fixedByName = fixedByName;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
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
	
	public Date getPullRequestDate() {
		return pullRequestDate;
	}

	public void setPullRequestDate(Date pullRequestDate) {
		this.pullRequestDate = pullRequestDate;
	}
	
}
