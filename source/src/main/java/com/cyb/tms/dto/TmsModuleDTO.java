package com.cyb.tms.dto;

import java.util.HashSet;
import java.util.Set;

import com.cyb.tms.dto.base.BaseDTO;
import com.cyb.tms.entity.TmsProject;
import com.cyb.tms.entity.TmsUsers;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class TmsModuleDTO extends BaseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1634237628354585862L;
	private Long id;
	private TmsProject tmsProject;
	private String moduleName;
	private String moduleDescription;
	private Long projectId;
	private Set<TmsUsers> tmsUserses = new HashSet<TmsUsers>(0);
	
	public TmsModuleDTO() {
		super();
	}
	
	
	public TmsModuleDTO(Long id, TmsProject tmsProject, String moduleName, String moduleDescription, Long projectId,
			Set<TmsUsers> tmsUserses) {
		super();
		this.id = id;
		this.tmsProject = tmsProject;
		this.moduleName = moduleName;
		this.moduleDescription = moduleDescription;
		this.projectId = projectId;
		this.tmsUserses = tmsUserses;
	}


	public TmsModuleDTO(Long id, TmsProject tmsProject, String moduleName, String moduleDescription,
			Set<TmsUsers> tmsUserses) {
		super();
		this.id = id;
		this.tmsProject = tmsProject;
		this.moduleName = moduleName;
		this.moduleDescription = moduleDescription;
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


	public String getModuleName() {
		return moduleName;
	}


	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}


	public String getModuleDescription() {
		return moduleDescription;
	}


	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
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
