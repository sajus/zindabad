package com.cyb.tms.dto;

import java.util.Date;

public class TmsEffortsDTO {
	
	
	
	private Long effortId;
	private Long projectId;
	private Long subtaskId;
	private Date loggedDate;
	private Double loggedHours;
	
	public TmsEffortsDTO() {
		super();
	}

	public TmsEffortsDTO(Long effortId, Long projectId, Long subtaskId, Date loggedDate, Double loggedHours) {
		super();
		this.effortId = effortId;
		this.projectId = projectId;
		this.subtaskId = subtaskId;
		this.loggedDate = loggedDate;
		this.loggedHours = loggedHours;
	}

	public Long getEffortId() {
		return effortId;
	}

	public void setEffortId(Long effortId) {
		this.effortId = effortId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getSubtaskId() {
		return subtaskId;
	}

	public void setSubtaskId(Long subtaskId) {
		this.subtaskId = subtaskId;
	}

	public Date getLoggedDate() {
		return loggedDate;
	}

	public void setLoggedDate(Date loggedDate) {
		this.loggedDate = loggedDate;
	}

	public Double getLoggedHours() {
		return loggedHours;
	}

	public void setLoggedHours(Double loggedHours) {
		this.loggedHours = loggedHours;
	}

	
	
	
	
}
