package com.cyb.tms.dto;

import java.util.Date;

import com.cyb.tms.dto.base.BaseDTO;

public class SubtaskDTO extends BaseDTO {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3839609465340330154L;
	private Long subtaskId;
	private Long storyId;
	private Long projectId;
	private int efforts;
	private String jiraId;
	private String scope;
	private String type;
	private String status;
	private Date assignedDate;
	private Date createdDate;
	private Long userId;
	

	public SubtaskDTO() {
		super();
	}

	public SubtaskDTO(Long userId, Long subtaskId, Long storyId, Long projectId, int efforts, String jiraId, String scope,
			String type, String status, Date assignedDate, Date createdDate) {
		super();
		this.subtaskId = subtaskId;
		this.storyId = storyId;
		this.projectId = projectId;
		this.efforts = efforts;
		this.jiraId = jiraId;
		this.scope = scope;
		this.type = type;
		this.status = status;
		this.assignedDate = assignedDate;
		this.createdDate = createdDate;
		this.userId = userId;
	}

	public Long getSubtaskId() {
		return subtaskId;
	}

	public void setSubtaskId(Long subtaskId) {
		this.subtaskId = subtaskId;
	}

	public Long getStoryId() {
		return storyId;
	}

	public void setStoryId(Long storyId) {
		this.storyId = storyId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public int getEfforts() {
		return efforts;
	}

	public void setEfforts(int efforts) {
		this.efforts = efforts;
	}

	public String getJiraId() {
		return jiraId;
	}

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}
}