package com.cyb.tms.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dao.TmsStoryDAO;
import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.dto.TmsSprintDTO;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.entity.TmsTaskType;
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
	private SessionFactory sessionFactory;

	@Override
    public long createStory(StoryDTO storyDTO) {
		int flag1=0;
        List<TmsStoryMst> allStories = getAllStories();
        for(TmsStoryMst storyMst:allStories){
           if(storyDTO.getJiraId().equals(storyMst.getJiraId())){
                  flag1=1;
                  return 0;
           }  
        } 
        if(flag1==0){
           TmsStatusMst status = hibernateUtil.findByPropertyName("status", backlog, TmsStatusMst.class);
           TmsModule module = hibernateUtil.fetchById(storyDTO.getModuleId(), TmsModule.class);
           TmsTaskType taskType = hibernateUtil.fetchById(storyDTO.getTaskTypeId(), TmsTaskType.class);
           TmsStoryMst tmsStoryMst = new TmsStoryMst();
           BeanUtils.copyProperties(storyDTO, tmsStoryMst);
           tmsStoryMst.setTmsModule(module);
           tmsStoryMst.setTmsTaskType(taskType);
           UserStoryStaus userStoryStatus = new UserStoryStaus();
           userStoryStatus.setCreatedDate(storyDTO.getCreatedDate());
           userStoryStatus.setType(story);
           userStoryStatus.setTmsStatusMst(status);
           userStoryStatus.setTmsStoryMst(tmsStoryMst);
           tmsStoryMst.getUserStoryStauses().add(userStoryStatus);
           return (Long)hibernateUtil.create(tmsStoryMst);
           }
        else {
        	return 0;
        }
     }


	@Override
	public void addToCurrentSprint(List<StoryDTO> storyDTOs, Long projectId, Long assignToId, Long modifiedById) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			TmsStatusMst status = hibernateUtil.findByProperty(session, "status", todo, TmsStatusMst.class);
			TmsUsers assignedTo = (TmsUsers) session.get(TmsUsers.class, assignToId);//hibernateUtil.fetchById(assignToId, TmsUsers.class);
			TmsUsers modifiedBy = (TmsUsers) session.get(TmsUsers.class, modifiedById);//hibernateUtil.fetchById(modifiedById, TmsUsers.class);
			TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
			for (StoryDTO storyDTO : storyDTOs) {
				UserStoryStaus userStoryStatus = new UserStoryStaus();
				TmsStoryMst tmsStoryMst = (TmsStoryMst) session.get(TmsStoryMst.class, storyDTO.getStoryId());
				userStoryStatus.setTmsSprintMst(sprint);
				userStoryStatus.setTmsStoryMst(tmsStoryMst);
				userStoryStatus.setModifiedDate(new Date());
				userStoryStatus.setAssignedDate(new Date());
				userStoryStatus.setTmsUsersByAssignedTo(assignedTo);
				userStoryStatus.setTmsUsersByModifiedBy(modifiedBy);
				userStoryStatus.setTmsStatusMst(status);
				userStoryStatus.setType(story);
				session.save(userStoryStatus);
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			try {
				session.flush();
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			session = null;
			tx = null;
		}
	}

	//------------------- Update a Story --------------------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public void updateStoryStatus(StoryDTO storyDTO) {
		TmsStatusMst status = hibernateUtil.findByPropertyName("status", storyDTO.getStatus(), TmsStatusMst.class);
		TmsUsers user = hibernateUtil.fetchById( storyDTO.getUserId(), TmsUsers.class);
		TmsStoryMst tmsStoryMst = hibernateUtil.fetchById(storyDTO.getStoryId(), TmsStoryMst.class);
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(storyDTO.getProjectId());
		UserStoryStaus previousStatus = getLatestStatus(tmsStoryMst);
		UserStoryStaus userStoryStatus = new UserStoryStaus();
		userStoryStatus.setTmsSprintMst(sprint);
		userStoryStatus.setTmsStatusMst(status);
		userStoryStatus.setType(story);
		userStoryStatus.setTmsStoryMst(tmsStoryMst);
		userStoryStatus.setModifiedDate(new Date());
		userStoryStatus.setTmsUsersByModifiedBy(user);
		if(!storyDTO.getStatus().equalsIgnoreCase(backlog) && previousStatus != null) {
			userStoryStatus.setTmsUsersByAssignedTo(previousStatus.getTmsUsersByAssignedTo());
			userStoryStatus.setAssignedDate(previousStatus.getAssignedDate());
		} else {
				// TODO move corresponding subtasks to backlog
		}
			hibernateUtil.create(userStoryStatus);
		}
		
	// -------------------Edit backlog Story---------------
	@SuppressWarnings("unchecked")
	@Override
	public void editStory(StoryDTO storyDTO) {
		TmsStoryMst tmsStoryMst = hibernateUtil.fetchById(storyDTO.getStoryId(), TmsStoryMst.class);
		TmsModule module = hibernateUtil.fetchById(storyDTO.getModuleId(), TmsModule.class);
		TmsTaskType taskType = hibernateUtil.fetchById(storyDTO.getTaskTypeId(), TmsTaskType.class);
		tmsStoryMst.setJiraId(storyDTO.getJiraId());
		tmsStoryMst.setCreatedDate(storyDTO.getCreatedDate());
		tmsStoryMst.setStoryPoint(storyDTO.getStoryPoint());
		tmsStoryMst.setTmsModule(module);
		tmsStoryMst.setTmsTaskType(taskType);
		hibernateUtil.update(tmsStoryMst);
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
			List<Long> backLogIds = getBackLogStorieIds();
			List<Long> storyIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsStoryMst", "story")
					.createAlias("tmsUsersByAssignedTo", "users")
					.setProjection( Projections.distinct(Projections.property("story.storyId")))
					.add(backLogIds.size() > 0 ? Restrictions.not(Restrictions.in("story.storyId", backLogIds)): Restrictions.sqlRestriction("(1=1)"))
					.add(Restrictions.eq("users.id", userId))
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
		Object[] status = {backlog, closed};
		if(sprint != null) {
			List<Long> storyIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
					.createAlias("tmsStoryMst", "story")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsStatusMst", "tsm")
					.add(Subqueries.propertyIn("uss.id",  DetachedCriteria.forClass(UserStoryStaus.class, "status")
							.add(Restrictions.eqProperty("uss.tmsStoryMst", "status.tmsStoryMst"))
							.setProjection(Projections.max("status.id"))))
							.add(Restrictions.not(Restrictions.in("tsm.status", status)))
					.add(Restrictions.eq("sprint.sprintId", sprint.getSprintId()))
					.setProjection( Projections.distinct(Projections.property("story.storyId"))).list();
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
		List<Long> storyIds = getBackLogStorieIds();
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

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Long> getBackLogStorieIds() {
		List<Long> storyIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
				.createAlias("tmsStoryMst", "story")
				.createAlias("tmsStatusMst", "tsm")
				.add(Subqueries.propertyIn("uss.id",  DetachedCriteria.forClass(UserStoryStaus.class, "status")
						.add(Restrictions.eqProperty("uss.tmsStoryMst", "status.tmsStoryMst"))
						.setProjection(Projections.max("status.id"))))
						.add(Restrictions.eq("tsm.status", backlog))
						.setProjection( Projections.distinct(Projections.property("story.storyId"))).list();
		return storyIds;
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
			if ( tmsStoryMst.getTmsTaskType() != null) {
				map.put("taskTypeId", tmsStoryMst.getTmsTaskType().getId());
				map.put("taskTypeName", tmsStoryMst.getTmsTaskType().getTaskTypeName());
			}
			map.put("createdDate", tmsStoryMst.getCreatedDate());
			
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
	
	private UserStoryStaus getLatestStatus(TmsStoryMst tmsStoryMst) {
		List<UserStoryStaus> userStoryStatusList = new ArrayList<UserStoryStaus>();
		userStoryStatusList.addAll(tmsStoryMst.getUserStoryStauses());
		Collections.sort(userStoryStatusList);
		return (UserStoryStaus) userStoryStatusList.get(userStoryStatusList.size() - 1);
	}
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedHashMap<String, Object>> getAllCurrentUserStoriesBySprint(Long projectId) {
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		if(sprint != null) {
			List<Long> backLogIds = getBackLogStorieIds();
			List<Long> storyIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsStoryMst", "story")
					//.createAlias("tmsUsersByAssignedTo", "users")
					.setProjection( Projections.distinct(Projections.property("story.storyId")))
					.add(backLogIds.size() > 0 ? Restrictions.not(Restrictions.in("story.storyId", backLogIds)): Restrictions.sqlRestriction("(1=1)"))
					//.add(Restrictions.eq("users.id", userId))
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
}
