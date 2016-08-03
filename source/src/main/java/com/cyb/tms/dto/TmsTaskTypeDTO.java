package com.cyb.tms.dto;

import java.util.HashSet;
import java.util.Set;

import com.cyb.tms.dto.base.BaseDTO;
import com.cyb.tms.entity.TmsProject;
import com.cyb.tms.entity.TmsUsers;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class TmsTaskTypeDTO extends BaseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6727496122927649297L;
	/**
	 * 
	 */
	
	private Long id;
	private TmsProject tmsProject;
	private String taskTypeName;
	private String taskTypeDescription;
	private Long projectId;
	private Set<TmsUsers> tmsUserses = new HashSet<TmsUsers>(0);

	public TmsTaskTypeDTO() {
		super();
	}
	
	public TmsTaskTypeDTO(Long id, TmsProject tmsProject, String taskTypeName, String taskTypeDescription,
			Long projectId, Set<TmsUsers> tmsUserses) {
		super();
		this.id = id;
		this.tmsProject = tmsProject;
		this.taskTypeName = taskTypeName;
		this.taskTypeDescription = taskTypeDescription;
		this.projectId = projectId;
		this.tmsUserses = tmsUserses;
	}

	public TmsTaskTypeDTO(Long id, TmsProject tmsProject, String taskTypeName, String taskTypeDescription,
			Set<TmsUsers> tmsUserses) {
		super();
		this.id = id;
		this.tmsProject = tmsProject;
		this.taskTypeName = taskTypeName;
		this.taskTypeDescription = taskTypeDescription;
		this.tmsUserses = tmsUserses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public TmsProject getTmsProject() {
		return tmsProject;
	}

	public void setTmsProject(TmsProject tmsProject) {
		this.tmsProject = tmsProject;
	}

	public String getTaskTypeName() {
		return taskTypeName;
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}

	public String getTaskTypeDescription() {
		return taskTypeDescription;
	}

	public void setTaskTypeDescription(String taskTypeDescription) {
		this.taskTypeDescription = taskTypeDescription;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Set<TmsUsers> getTmsUserses() {
		return tmsUserses;
	}

	public void setTmsUserses(Set<TmsUsers> tmsUserses) {
		this.tmsUserses = tmsUserses;
	}
	
}	