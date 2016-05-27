package com.cyb.tms.dto;

import com.cyb.tms.dto.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class TmsUsersDTO extends BaseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4685569593367824295L;
	private Long id;
	private TmsProjectDTO tmsProjectDTO;
	private String email;
	private String userName;
	private String userRole;
	private String projectName;
	private Long projectId;
	
	public TmsUsersDTO() {
		super();
	}

	public TmsUsersDTO(Long id, TmsProjectDTO tmsProjectDTO, String email, String userName, String userRole) {
		super();
		this.id = id;
		this.tmsProjectDTO = tmsProjectDTO;
		this.email = email;
		this.userName = userName;
		this.userRole = userRole;
	}

	 
	public TmsUsersDTO(Long id, TmsProjectDTO tmsProjectDTO, String email, String userName, String userRole,
			String projectName, Long projectId) {
		super();
		this.id = id;
		this.tmsProjectDTO = tmsProjectDTO;
		this.email = email;
		this.userName = userName;
		this.userRole = userRole;
		this.projectName = projectName;
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public TmsProjectDTO getTmsProjectDTO() {
		return tmsProjectDTO;
	}

	public void setTmsProjectDTO(TmsProjectDTO tmsProjectDTO) {
		this.tmsProjectDTO = tmsProjectDTO;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
	

}
