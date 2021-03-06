package com.cyb.tms.entity;
// Generated May 19, 2016 12:17:39 PM by Hibernate Tools 4.3.1.Final

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
import javax.persistence.UniqueConstraint;

import com.cyb.tms.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


/**
 * TmsUsers generated by hbm2java
 */
@Entity
@Table(name = "tms_users", catalog = "TaskManagement", uniqueConstraints = {
		@UniqueConstraint(columnNames = "USER_NAME"), @UniqueConstraint(columnNames = "EMAIL") })
public class TmsUsers extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9198550957284072520L;
	private Long id;
	private TmsProject tmsProject;
	private String email;
	private String isActive;
	private String password;
	private String userName;
	private String userRole;
	private Set<TmsLeaveMst> tmsLeaveMsts = new HashSet<TmsLeaveMst>(0);
	private Set<UserStoryStaus> userStoryStausesForAssignedTo = new HashSet<UserStoryStaus>(
			0);
	private Set<TmsCodeReview> tmsCodeReviewsForFixedBy = new HashSet<TmsCodeReview>(
			0);
	private Set<TmsCodeReview> tmsCodeReviewsForDeveloper = new HashSet<TmsCodeReview>(
			0);
	private Set<TmsCodeReview> tmsCodeReviewsForReviewer = new HashSet<TmsCodeReview>(
			0);
	private Set<UserStoryStaus> userStoryStausesForModifiedBy = new HashSet<UserStoryStaus>(
			0);

	public TmsUsers() {
	}

	public TmsUsers(Long id, TmsProject tmsProject, String email, String isActive, String password, String userName,
		String userRole, Set<TmsLeaveMst> tmsLeaveMsts, Set<UserStoryStaus> userStoryStausesForAssignedTo,
		Set<TmsCodeReview> tmsCodeReviewsForFixedBy, Set<TmsCodeReview> tmsCodeReviewsForDeveloper,
		Set<TmsCodeReview> tmsCodeReviewsForReviewer, Set<UserStoryStaus> userStoryStausesForModifiedBy) {
		super();
		this.id = id;
		this.tmsProject = tmsProject;
		this.email = email;
		this.isActive = isActive;
		this.password = password;
		this.userName = userName;
		this.userRole = userRole;
		this.tmsLeaveMsts = tmsLeaveMsts;
		this.userStoryStausesForAssignedTo = userStoryStausesForAssignedTo;
		this.tmsCodeReviewsForFixedBy = tmsCodeReviewsForFixedBy;
		this.tmsCodeReviewsForDeveloper = tmsCodeReviewsForDeveloper;
		this.tmsCodeReviewsForReviewer = tmsCodeReviewsForReviewer;
		this.userStoryStausesForModifiedBy = userStoryStausesForModifiedBy;
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

	@Column(name = "EMAIL", unique = true, nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "IS_ACTIVE", nullable = false, length = 9)
	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Column(name = "PASSWORD", nullable = false, length = 80)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "USER_NAME", unique = true, nullable = false, length = 45)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_ROLE", nullable = false, length = 9)
	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsers")
	public Set<TmsLeaveMst> getTmsLeaveMsts() {
		return this.tmsLeaveMsts;
	}

	public void setTmsLeaveMsts(Set<TmsLeaveMst> tmsLeaveMsts) {
		this.tmsLeaveMsts = tmsLeaveMsts;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsersByAssignedTo")
	public Set<UserStoryStaus> getUserStoryStausesForAssignedTo() {
		return this.userStoryStausesForAssignedTo;
	}

	public void setUserStoryStausesForAssignedTo(
			Set<UserStoryStaus> userStoryStausesForAssignedTo) {
		this.userStoryStausesForAssignedTo = userStoryStausesForAssignedTo;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsersByFixedBy")
	public Set<TmsCodeReview> getTmsCodeReviewsForFixedBy() {
		return this.tmsCodeReviewsForFixedBy;
	}

	public void setTmsCodeReviewsForFixedBy(
			Set<TmsCodeReview> tmsCodeReviewsForFixedBy) {
		this.tmsCodeReviewsForFixedBy = tmsCodeReviewsForFixedBy;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsersByDeveloper")
	public Set<TmsCodeReview> getTmsCodeReviewsForDeveloper() {
		return this.tmsCodeReviewsForDeveloper;
	}

	public void setTmsCodeReviewsForDeveloper(
			Set<TmsCodeReview> tmsCodeReviewsForDeveloper) {
		this.tmsCodeReviewsForDeveloper = tmsCodeReviewsForDeveloper;
	}
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsersByReviewer")
	public Set<TmsCodeReview> getTmsCodeReviewsForReviewer() {
		return tmsCodeReviewsForReviewer;
	}

	public void setTmsCodeReviewsForReviewer(Set<TmsCodeReview> tmsCodeReviewsForReviewer) {
		this.tmsCodeReviewsForReviewer = tmsCodeReviewsForReviewer;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsersByModifiedBy")
	public Set<UserStoryStaus> getUserStoryStausesForModifiedBy() {
		return this.userStoryStausesForModifiedBy;
	}

	public void setUserStoryStausesForModifiedBy(
			Set<UserStoryStaus> userStoryStausesForModifiedBy) {
		this.userStoryStausesForModifiedBy = userStoryStausesForModifiedBy;
	}
}
