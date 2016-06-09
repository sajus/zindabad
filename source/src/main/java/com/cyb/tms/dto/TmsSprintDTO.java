package com.cyb.tms.dto;

import java.util.Date;

import com.cyb.tms.dto.base.BaseDTO;

public class TmsSprintDTO extends BaseDTO {
	
	private Long sprintId;
	private Date sprintEndDate;
	private int sprintHours;
	private String sprintName;
	private Date sprintStartDate;
	private String sprintStatus;
	private int sprintVelocity;
	private Long projectId;
	
	public TmsSprintDTO() {
        super();
  }

	public TmsSprintDTO(Long sprintId, Date sprintEndDate, int sprintHours, String sprintName, Date sprintStartDate,
			String sprintStatus, int sprintVelocity, Long projectId) {
		super();
		this.sprintId = sprintId;
		this.sprintEndDate = sprintEndDate;
		this.sprintHours = sprintHours;
		this.sprintName = sprintName;
		this.sprintStartDate = sprintStartDate;
		this.sprintStatus = sprintStatus;
		this.sprintVelocity = sprintVelocity;
		this.projectId = projectId;
	}

	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	public Date getSprintEndDate() {
		return sprintEndDate;
	}

	public void setSprintEndDate(Date sprintEndDate) {
		this.sprintEndDate = sprintEndDate;
	}

	public int getSprintHours() {
		return sprintHours;
	}

	public void setSprintHours(int sprintHours) {
		this.sprintHours = sprintHours;
	}

	public String getSprintName() {
		return sprintName;
	}

	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}

	public Date getSprintStartDate() {
		return sprintStartDate;
	}

	public void setSprintStartDate(Date sprintStartDate) {
		this.sprintStartDate = sprintStartDate;
	}

	public String getSprintStatus() {
		return sprintStatus;
	}

	public void setSprintStatus(String sprintStatus) {
		this.sprintStatus = sprintStatus;
	}

	public int getSprintVelocity() {
		return sprintVelocity;
	}

	public void setSprintVelocity(int sprintVelocity) {
		this.sprintVelocity = sprintVelocity;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}



}
