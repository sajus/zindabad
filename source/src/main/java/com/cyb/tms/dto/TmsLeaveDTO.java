package com.cyb.tms.dto;

import java.util.Date;

import com.cyb.tms.dto.base.BaseDTO;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsUsers;

public class TmsLeaveDTO extends BaseDTO {
	
	private Long leaveId;
	private Long projectId;
	private Long userId;
	private Date startDate;
	private Date endDate;
	private String reason;
	private int duration;
	private String status;

	
	public TmsLeaveDTO() {
		super();
	}
	
	public TmsLeaveDTO(Long leaveId, Long projectId, Long userId, Date startDate, Date endDate, String reason,
			int duration, String status) {
			super();
			this.leaveId = leaveId;
			this.projectId = projectId;
			this.userId = userId;
			this.startDate = startDate;
			this.endDate = endDate;
			this.reason = reason;
			this.duration = duration;
			this.status = status;
		}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getLeaveId() {
		return leaveId;
	}


	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}


	public Long getProjectId() {
		return projectId;
	}


	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}

}
