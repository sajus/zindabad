package com.cyb.tms.entity;
// Generated May 17, 2016 12:38:28 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;

import com.cyb.tms.entity.base.BaseEntity;

/**
 * TmsSubtask generated by hbm2java
 */
@Entity
@Table(name = "tms_subtask", catalog = "TaskManagement")
@FilterDef(name = TmsSubtask.LATEST_STATUS_FILTER)
public class TmsSubtask extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9222900952318082035L;
	public static final String LATEST_STATUS_FILTER = "latestStatusFilter";
	private Long subtaskId;
	private TmsStoryMst tmsStoryMst;
	private int efforts;
	private String jiraId;
	private String scope;
	private String type;
	private Set<TmsCodeReview> tmsCodeReviews = new HashSet<TmsCodeReview>(0);
	private Set<UserStoryStaus> userStoryStauses = new HashSet<UserStoryStaus>(
			0);
	private Set<TmsEfforts> tmsEffortses = new HashSet<TmsEfforts>(0);

	public TmsSubtask() {
	}

	public TmsSubtask(TmsStoryMst tmsStoryMst, int efforts, String jiraId,
			String scope, String type) {
		this.tmsStoryMst = tmsStoryMst;
		this.efforts = efforts;
		this.jiraId = jiraId;
		this.scope = scope;
		this.type = type;
	}

	public TmsSubtask(TmsStoryMst tmsStoryMst, int efforts, String jiraId,
			String scope, String type, Set<TmsCodeReview> tmsCodeReviews,
			Set<UserStoryStaus> userStoryStauses, Set<TmsEfforts> tmsEffortses) {
		this.tmsStoryMst = tmsStoryMst;
		this.efforts = efforts;
		this.jiraId = jiraId;
		this.scope = scope;
		this.type = type;
		this.tmsCodeReviews = tmsCodeReviews;
		this.userStoryStauses = userStoryStauses;
		this.tmsEffortses = tmsEffortses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SUBTASK_ID", unique = true, nullable = false)
	public Long getSubtaskId() {
		return this.subtaskId;
	}

	public void setSubtaskId(Long subtaskId) {
		this.subtaskId = subtaskId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORY_ID", nullable = false)
	public TmsStoryMst getTmsStoryMst() {
		return this.tmsStoryMst;
	}

	public void setTmsStoryMst(TmsStoryMst tmsStoryMst) {
		this.tmsStoryMst = tmsStoryMst;
	}

	@Column(name = "EFFORTS", nullable = false)
	public int getEfforts() {
		return this.efforts;
	}

	public void setEfforts(int efforts) {
		this.efforts = efforts;
	}

	@Column(name = "JIRA_ID", nullable = false, length = 45)
	public String getJiraId() {
		return this.jiraId;
	}

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	@Column(name = "SCOPE", nullable = false, length = 10)
	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Column(name = "TYPE", nullable = false, length = 8)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsSubtask")
	public Set<TmsCodeReview> getTmsCodeReviews() {
		return this.tmsCodeReviews;
	}

	public void setTmsCodeReviews(Set<TmsCodeReview> tmsCodeReviews) {
		this.tmsCodeReviews = tmsCodeReviews;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tmsSubtask", cascade=CascadeType.ALL)
	@Filter(name = LATEST_STATUS_FILTER, condition = "ID = (select max(uss.ID) from user_story_staus uss where uss.SUBTASK_ID = SUBTASK_ID)")
	public Set<UserStoryStaus> getUserStoryStauses() {
		return this.userStoryStauses;
	}

	public void setUserStoryStauses(Set<UserStoryStaus> userStoryStauses) {
		this.userStoryStauses = userStoryStauses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsSubtask")
	public Set<TmsEfforts> getTmsEffortses() {
		return this.tmsEffortses;
	}

	public void setTmsEffortses(Set<TmsEfforts> tmsEffortses) {
		this.tmsEffortses = tmsEffortses;
	}

}
