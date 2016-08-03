package com.cyb.tms.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cyb.tms.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * TmsModule generated by hbm2java
 */
@Entity
@Table(name = "tms_tasktype", catalog = "TaskManagement")
public class TmsTaskType extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8019506905544401038L;
	/**
	 * 
	 */
	private Long id;
	private TmsProject tmsProject;
	private String taskTypeName;
	private String taskTypeDescription;
	private Set<TmsStoryMst> tmsStoryMsts = new HashSet<TmsStoryMst>(0);

	public TmsTaskType() {
	}

	
	public TmsTaskType(Long id, TmsProject tmsProject, String taskTypeName, String taskTypeDescription,
			Set<TmsStoryMst> tmsStoryMsts) {
		super();
		this.id = id;
		this.tmsProject = tmsProject;
		this.taskTypeName = taskTypeName;
		this.taskTypeDescription = taskTypeDescription;
		this.tmsStoryMsts = tmsStoryMsts;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID")
	public TmsProject getTmsProject() {
		return this.tmsProject;
	}

	public void setTmsProject(TmsProject tmsProject) {
		this.tmsProject = tmsProject;
	}

	@Column(name = "TASKTYPE_NAME", length = 45)
	public String getTaskTypeName() {
		return this.taskTypeName;
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}

	@Column(name = "TASKTYPE_DESCRIPTION", length = 100)
	public String getTaskTypeDescription() {
		return this.taskTypeDescription;
	}

	public void setTaskTypeDescription(String taskTypeDescription) {
		this.taskTypeDescription = taskTypeDescription;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsTaskType")
	public Set<TmsStoryMst> getTmsStoryMsts() {
		return this.tmsStoryMsts;
	}

	public void setTmsStoryMsts(Set<TmsStoryMst> tmsStoryMsts) {
		this.tmsStoryMsts = tmsStoryMsts;
	}

}
