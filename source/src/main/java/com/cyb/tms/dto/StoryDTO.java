package com.cyb.tms.dto;

import java.util.Date;

import com.cyb.tms.dto.base.BaseDTO;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.entity.TmsUsers;

public class StoryDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2599696294099365925L;
	
	private Long storyId;
	private String userId;
	private String module;
	private String status;
	private Date assignedDate;
	private Date createdDate;
	private String jiraId;
	private int storyPoint;
	private Long projectId;
	
	public StoryDTO() {
		super();
	}

	public StoryDTO(Long storyId, String userId, String module, String status,
			Date assignedDate, Date createdDate, String jiraId, int storyPoint,
			Long projectId) {
		super();
		this.storyId = storyId;
		this.userId = userId;
		this.module = module;
		this.status = status;
		this.assignedDate = assignedDate;
		this.createdDate = createdDate;
		this.jiraId = jiraId;
		this.storyPoint = storyPoint;
		this.projectId = projectId;
	}

	public Long getStoryId() {
		return storyId;
	}

	public void setStoryId(Long storyId) {
		this.storyId = storyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getJiraId() {
		return jiraId;
	}

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	public int getStoryPoint() {
		return storyPoint;
	}

	public void setStoryPoint(int storyPoint) {
		this.storyPoint = storyPoint;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
}
