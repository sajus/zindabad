package com.cyb.tms.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dao.TmsSubTaskDAO;
import com.cyb.tms.dto.SubtaskDTO;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.entity.UserStoryStaus;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsSubTaskDAOImpl implements TmsSubTaskDAO {

	@Value("${tms.status.backlog}")
	private String backlog;
	
	@Value("${tms.status.closed}")
	private String closed;
	
	@Value("${tms.status.code_merged}")
	private String code_merged;

	@Autowired
	private HibernateUtil hibernateUtil;

	@Autowired
	private TmsSprintDAO tmsSprintDAO;

	@Override
	public long createSubtask(SubtaskDTO subtaskDTO) {
		TmsStatusMst status = hibernateUtil.findByPropertyName("status", backlog, TmsStatusMst.class);
		TmsStoryMst storyId = hibernateUtil.fetchById(subtaskDTO.getStoryId(), TmsStoryMst.class);
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(subtaskDTO.getProjectId());
		TmsSubtask subtask = new TmsSubtask();
		BeanUtils.copyProperties(subtaskDTO, subtask);
		subtask.setTmsStoryMst(storyId);
		UserStoryStaus userStoryStatus = new UserStoryStaus();
		userStoryStatus.setCreatedDate(subtaskDTO.getCreatedDate());
		userStoryStatus.setTmsSprintMst(sprint);
		userStoryStatus.setTmsStatusMst(status);
		userStoryStatus.setType(subtaskDTO.getType());
		userStoryStatus.setTmsSubtask(subtask);
		subtask.getUserStoryStauses().add(userStoryStatus);
		return (Long)hibernateUtil.create(subtask);
	}

	@Override
	public TmsSubtask updateSubtask(TmsSubtask subtask) {
		return hibernateUtil.update(subtask);
	}

	@Override
	public List<TmsSubtask> getAllSubtasks() {
		return hibernateUtil.fetchAll(TmsSubtask.class);
	}

	@Override
	public TmsSubtask getSubtask(long id) {
		return hibernateUtil.fetchById(id, TmsSubtask.class);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<String, Object>> getSubtasksBySprint(Long projectId) throws Exception {

		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		if(sprint != null) {
			List<Long> subtaskIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsSubtask", "sub")
					.setProjection( Projections.distinct(Projections.property("sub.subtaskId")))
					.add(Subqueries.propertyNotIn("sub.subtaskId",  DetachedCriteria.forClass(UserStoryStaus.class)
							.createAlias("tmsStatusMst", "tsm")
							.createAlias("tmsSubtask", "sub")
							.add(Restrictions.eq("tsm.status", backlog))
							.setProjection(Property.forName("sub.subtaskId"))))
							.add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())).list();
			if(subtaskIds.size() > 0) {
				return parseStories(getFilteredSubtasks(subtaskIds));
			} else {
				return null;
			}

		} else {
			throw new Exception("Sprint not found");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<String, Object>> getBackLogSubtasks(Long projectId) {
		List<Long> subtaskIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
				.createAlias("tmsStatusMst", "tsm")
				.createAlias("tmsSubtask", "sub")
				.setProjection( Projections.distinct(Projections.property("sub.subtaskId")))
				.add(Restrictions.eq("tsm.status", backlog)).list();
		if(subtaskIds.size() > 0) {
			return parseStories(getFilteredSubtasks(subtaskIds));
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<String, Object>> getCurrentUserSubTasksBySprintBy(Long userId, Long projectId) throws Exception {
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		if(sprint != null) {
			List<Long> subtaskIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsSubtask", "sub")
					.createAlias("tmsUsersByAssignedTo", "users")
					.setProjection( Projections.distinct(Projections.property("sub.subtaskId")))
					.add(Subqueries.propertyNotIn("sub.subtaskId",  DetachedCriteria.forClass(UserStoryStaus.class)
							.createAlias("tmsStatusMst", "tsm")
							.createAlias("tmsSubtask", "sub")
							.add(Restrictions.eq("tsm.status", backlog))
							.setProjection(Property.forName("sub.subtaskId"))))
					.add(Restrictions.eq("users.id", userId))
					.add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())).list();
			if(subtaskIds.size() > 0) {
				return parseStories(getFilteredSubtasks(subtaskIds));
			} else {
				return null;
			}

		} else {
			throw new Exception("Sprint not found");
		}
	}

	/**
	 * @param subtaskIds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<TmsSubtask> getFilteredSubtasks(List<Long> subtaskIds) {
		hibernateUtil.getCurrentSession().enableFilter(TmsSubtask.LATEST_STATUS_FILTER);
		List<TmsSubtask> subtasks = hibernateUtil.getCurrentSession().createCriteria(TmsSubtask.class)
				.add(Restrictions.in("subtaskId", subtaskIds)).list();
		hibernateUtil.getCurrentSession().disableFilter(TmsSubtask.LATEST_STATUS_FILTER);
		return subtasks;
	}

	private List<LinkedHashMap<String, Object>> parseStories(List<TmsSubtask> subtasks) {

		List<LinkedHashMap<String, Object>> userSubtasks = new ArrayList<LinkedHashMap<String, Object>>();
		for (TmsSubtask tmsSubtask : subtasks) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("subtaskId", tmsSubtask.getSubtaskId());
			map.put("jiraId", tmsSubtask.getJiraId());
			map.put("scope", tmsSubtask.getScope());
			map.put("type", tmsSubtask.getType());
			map.put("estEfforts", tmsSubtask.getEfforts());

			for (UserStoryStaus userStoryStaus : tmsSubtask.getUserStoryStauses()) {
				LinkedHashMap<String, Object> uss = new LinkedHashMap<String, Object>();
				uss.put("id", userStoryStaus.getId());
				uss.put("createdDate", userStoryStaus.getCreatedDate());
				uss.put("type", userStoryStaus.getType());
				uss.put("status", userStoryStaus.getTmsStatusMst().getStatus());
				if(userStoryStaus.getAssignedDate() != null) {
					uss.put("assignedDate", userStoryStaus.getAssignedDate());
				}
				if(userStoryStaus.getModifiedDate() != null) {
					uss.put("modifiedDate", userStoryStaus.getModifiedDate());
				}
				if(userStoryStaus.getTmsUsersByAssignedTo() != null) {
					uss.put("assignedTo", userStoryStaus.getTmsUsersByAssignedTo().getUserName());				
				}
				if(userStoryStaus.getTmsUsersByModifiedBy() != null) {
					uss.put("modifiedBy", userStoryStaus.getTmsUsersByModifiedBy().getUserName());				
				}
				map.put("userStoryStatus", uss);
			}

			userSubtasks.add(map);
		}
		return userSubtasks;
	}
}
