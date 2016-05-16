package com.cyb.tms.entity;
// Generated May 12, 2016 11:55:51 AM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cyb.tms.entity.base.BaseEntity;

/**
 * TmsProject generated by hbm2java
 */
@Entity
@Table(name = "tms_project", catalog = "TaskManagement")
public class TmsProject extends BaseEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5503305165264534310L;
	
	private Long pid;
	private String name;
	private Date startDate;
	private Set<TmsUsers> tmsUserses = new HashSet<TmsUsers>(0);

	public TmsProject() {
	}

	public TmsProject(String name, Date startDate, Set<TmsUsers> tmsUserses) {
		this.name = name;
		this.startDate = startDate;
		this.tmsUserses = tmsUserses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "PID", unique = true, nullable = false)
	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Column(name = "NAME", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsProject")
	public Set<TmsUsers> getTmsUserses() {
		return this.tmsUserses;
	}

	public void setTmsUserses(Set<TmsUsers> tmsUserses) {
		this.tmsUserses = tmsUserses;
	}

}
