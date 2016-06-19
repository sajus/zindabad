package com.cyb.tms.dto;

import java.util.Date;

import com.cyb.tms.dto.base.BaseDTO;

public class TmsProjectDTO extends BaseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6930326817116151864L;
	private Long pid;
	private String name;
	private Date startDate;
	private String projectDesc;
	private Date endDate;
	
	
	public TmsProjectDTO() {
		super();
	}
	
	
	public TmsProjectDTO(Long pid, String name, Date startDate, String projectDesc, Date endDate) {
		this.pid = pid;
		this.name = name;
		this.startDate = startDate;
		this.projectDesc = projectDesc;
		this.endDate = endDate;
	}


	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	

}
