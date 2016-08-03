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
	private Long userId;
	private String module;
	private String taskType;
	private String status;
	private Date assignedDate;
	private Date createdDate;
	private String jiraId;
	private int storyPoint;
	private Long projectId;
	private Long moduleId;
	private Long taskTypeId;
	
	public StoryDTO() {
		super();
	}

	public StoryDTO(Long storyId, Long userId, String module, String taskType, String status,
			Date assignedDate, Date createdDate, String jiraId, int storyPoint,
			Long projectId, Long moduleId, Long taskTypeId) {
		super();
		this.storyId = storyId;
		this.userId = userId;
		this.module = module;
		this.taskType = taskType;
		this.status = status;
		this.assignedDate = assignedDate;
		this.createdDate = createdDate;
		this.jiraId = jiraId;
		this.storyPoint = storyPoint;
		this.projectId = projectId;
		this.moduleId = moduleId;
		this.taskTypeId = taskTypeId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Long getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(Long taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public Long getStoryId() {
		return storyId;
	}

	public void setStoryId(Long storyId) {
		this.storyId = storyId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}	
}
