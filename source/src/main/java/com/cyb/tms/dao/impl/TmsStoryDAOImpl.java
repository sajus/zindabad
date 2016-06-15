package com.cyb.tms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsModuleDAO;
import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dao.TmsStatusDAO;
import com.cyb.tms.dao.TmsStoryDAO;
import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.entity.UserStoryStaus;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsStoryDAOImpl implements TmsStoryDAO {
	
	@Value("${tms.status.backlog}")
	private String backlog;
	
	@Value("${tms.status.todo}")
	private String todo;
	
	@Value("${tms.status.closed}")
	private String closed;
	
	@Value("${tms.status.code_merged}")
	private String code_merged;
	
	@Value("${tms.story}")
	private String story;

	@Autowired
	private HibernateUtil hibernateUtil;

	@Autowired
	private TmsSprintDAO tmsSprintDAO;
	
	@Autowired
	private TmsStatusDAO tmsStatusDAO;
	

	@Override
	public long createStory(StoryDTO storyDTO) {
		TmsStatusMst status = hibernateUtil.findByPropertyName("status", backlog, TmsStatusMst.class);
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(storyDTO.getProjectId());
		TmsModule module = hibernateUtil.findByPropertyName("moduleName", storyDTO.getModule(), TmsModule.class);
		TmsStoryMst tmsStoryMst = new TmsStoryMst();
		BeanUtils.copyProperties(storyDTO, tmsStoryMst);
		tmsStoryMst.setTmsModule(module);
		UserStoryStaus userStoryStatus = new UserStoryStaus();
		userStoryStatus.setCreatedDate(storyDTO.getCreatedDate());
		userStoryStatus.setType(story);
		userStoryStatus.setTmsSprintMst(sprint);
		userStoryStatus.setTmsStatusMst(status);
		userStoryStatus.setTmsStoryMst(tmsStoryMst);
		tmsStoryMst.getUserStoryStauses().add(userStoryStatus);
		return (Long)hibernateUtil.create(tmsStoryMst);
	}
	
	@Override
	public void addToCurrentSprint(List<StoryDTO> storyDTOs, Long projectId, Long assignToId, Long modifiedById) {
		TmsStatusMst status = hibernateUtil.findByPropertyName("status", todo, TmsStatusMst.class);
		TmsUsers assignedTo = hibernateUtil.fetchById(assignToId, TmsUsers.class);
		TmsUsers modifiedBy = hibernateUtil.fetchById(modifiedById, TmsUsers.class);
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		for (StoryDTO storyDTO : storyDTOs) {
			UserStoryStaus userStoryStatus = new UserStoryStaus();
			TmsStoryMst tmsStoryMst = hibernateUtil.fetchById(storyDTO.getStoryId(), TmsStoryMst.class);
			userStoryStatus.setTmsSprintMst(sprint);
			userStoryStatus.setTmsStoryMst(tmsStoryMst);
			userStoryStatus.setModifiedDate(new Date());
			userStoryStatus.setAssignedDate(new Date());
			userStoryStatus.setTmsUsersByAssignedTo(assignedTo);
			userStoryStatus.setTmsUsersByModifiedBy(modifiedBy);
			userStoryStatus.setTmsStatusMst(status);
			userStoryStatus.setType(story);
			hibernateUtil.create(tmsStoryMst);
		}
	}

	@Override
	public TmsStoryMst updateStory(TmsStoryMst story) {
		return hibernateUtil.update(story);
	}

	@Override
	public List<TmsStoryMst> getAllStories() {
		return hibernateUtil.fetchAll(TmsStoryMst.class);
	}

	@Override
	public TmsStoryMst getSprint(long id) {
		return hibernateUtil.fetchById(id, TmsStoryMst.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<String, Object>> getStoriesBySprint(Long projectId) throws Exception {

		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		if(sprint != null) {
			List<Long> storyIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsStoryMst", "story")
					.setProjection( Projections.distinct(Projections.property("story.storyId")))
					.add(Subqueries.propertyNotIn("story.storyId",  DetachedCriteria.forClass(UserStoryStaus.class)
							.createAlias("tmsStatusMst", "tsm")
							.createAlias("tmsStoryMst", "story")
							.add(Restrictions.eq("tsm.status", backlog))
					        .setProjection(Property.forName("story.storyId"))))
					.add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())).list();
			if(storyIds.size() > 0) {
				hibernateUtil.getCurrentSession().enableFilter(TmsStoryMst.LATEST_STATUS_FILTER);
				List<TmsStoryMst> stories = hibernateUtil.getCurrentSession().createCriteria(TmsStoryMst.class)
											.add(Restrictions.in("storyId", storyIds)).list();
				hibernateUtil.getCurrentSession().disableFilter(TmsStoryMst.LATEST_STATUS_FILTER);
				return parseStories(stories);
			} else {
				return null;
			}

		} else {
			throw new Exception("Sprint not found");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<String, Object>> getCurrentUserStoriesBySprint(
			Long userId, Long projectId) {
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		if(sprint != null) {
			List<Long> storyIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsStoryMst", "story")
					.createAlias("tmsUsersByAssignedTo", "user")
					.setProjection( Projections.distinct(Projections.property("story.storyId")))
					.add(Subqueries.propertyNotIn("story.storyId",  DetachedCriteria.forClass(UserStoryStaus.class)
							.createAlias("tmsStatusMst", "tsm")
							.createAlias("tmsStoryMst", "story")
							.add(Restrictions.eq("tsm.status", backlog))
					        .setProjection(Property.forName("story.storyId"))))
					.add(Restrictions.eq("user.id", userId))
					.add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())).list();
			if(storyIds.size() > 0) {
				hibernateUtil.getCurrentSession().enableFilter(TmsStoryMst.LATEST_STATUS_FILTER);
				List<TmsStoryMst> stories = hibernateUtil.getCurrentSession().createCriteria(TmsStoryMst.class)
											.add(Restrictions.in("storyId", storyIds)).list();
				hibernateUtil.getCurrentSession().disableFilter(TmsStoryMst.LATEST_STATUS_FILTER);
				return parseStories(stories);
			} else {
				return null;
			}

		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getIncompleteStoriesInSprint(Long projectId) {
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		Object[] status = {backlog, closed, code_merged};
		if(sprint != null) {
			List<Long> storyIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsStoryMst", "story")
					.setProjection( Projections.distinct(Projections.property("story.storyId")))
					.add(Subqueries.propertyNotIn("story.storyId",  DetachedCriteria.forClass(UserStoryStaus.class)
							.createAlias("tmsStatusMst", "tsm")
							.createAlias("tmsStoryMst", "story")
							.add(Restrictions.in("tsm.status", status))
					        .setProjection(Property.forName("story.storyId"))))
					.add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())).list();
			if(storyIds.size() > 0) {
				hibernateUtil.getCurrentSession().enableFilter(TmsStoryMst.LATEST_STATUS_FILTER);
				List<TmsStoryMst> stories = hibernateUtil.getCurrentSession().createCriteria(TmsStoryMst.class)
											.add(Restrictions.in("storyId", storyIds)).list();
				hibernateUtil.getCurrentSession().disableFilter(TmsStoryMst.LATEST_STATUS_FILTER);
				return getIncompleteStoryIds(stories);
			} 
		}
		return null; 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<String, Object>> getBackLogStories(Long projectId)
			throws Exception {
		List<Long> storyIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
				.createAlias("tmsStatusMst", "tsm")
				.createAlias("tmsStoryMst", "story")
				.setProjection( Projections.distinct(Projections.property("story.storyId")))
				.add(Restrictions.eq("tsm.status", backlog)).list();
		if(storyIds.size() > 0) {
			hibernateUtil.getCurrentSession().enableFilter(TmsStoryMst.LATEST_STATUS_FILTER);
			List<TmsStoryMst> stories = hibernateUtil.getCurrentSession().createCriteria(TmsStoryMst.class)
										.add(Restrictions.in("storyId", storyIds)).list();
			hibernateUtil.getCurrentSession().disableFilter(TmsStoryMst.LATEST_STATUS_FILTER);
		
			return parseStories(stories);
		} else {
			return null;
		}
	}
		
	private List<LinkedHashMap<String, Object>> parseStories(List<TmsStoryMst> stories) {

		List<LinkedHashMap<String, Object>> userStories = new ArrayList<LinkedHashMap<String, Object>>();
		for (TmsStoryMst tmsStoryMst : stories) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("storyId", tmsStoryMst.getStoryId());
			map.put("jiraId", tmsStoryMst.getJiraId());
			map.put("storyPoint", tmsStoryMst.getStoryPoint());
			map.put("moduleId", tmsStoryMst.getTmsModule().getId());
			map.put("moduleName", tmsStoryMst.getTmsModule().getModuleName());
			
			for (UserStoryStaus userStoryStatus : tmsStoryMst.getUserStoryStauses()) {
				LinkedHashMap<String, Object> uss = new LinkedHashMap<String, Object>();
				uss.put("id", userStoryStatus.getId());
				uss.put("createdDate", userStoryStatus.getCreatedDate());
				uss.put("type", userStoryStatus.getType());
				uss.put("status", userStoryStatus.getTmsStatusMst().getStatus());
				if(userStoryStatus.getAssignedDate() != null) {
					uss.put("assignedDate", userStoryStatus.getAssignedDate());
				}
				if(userStoryStatus.getModifiedDate() != null) {
					uss.put("modifiedDate", userStoryStatus.getModifiedDate());
				}
				if(userStoryStatus.getTmsUsersByAssignedTo() != null) {
					uss.put("assignedTo", userStoryStatus.getTmsUsersByAssignedTo().getUserName());				
				}
				if(userStoryStatus.getTmsUsersByModifiedBy() != null) {
					uss.put("modifiedBy", userStoryStatus.getTmsUsersByModifiedBy().getUserName());				
				}
				map.put("userStoryStatus", uss);
			}	
			userStories.add(map);
			}
		return userStories;
	}
	
	private List<String> getIncompleteStoryIds(List<TmsStoryMst> stories) {
		List<String> jiraIds = new ArrayList<String>();
		for (TmsStoryMst tmsStoryMst : stories) {
			jiraIds.add(tmsStoryMst.getJiraId());
		}
		return jiraIds;
	}

	
}
