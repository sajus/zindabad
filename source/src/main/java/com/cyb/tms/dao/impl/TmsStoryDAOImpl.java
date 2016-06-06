package com.cyb.tms.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;










import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dao.TmsStoryDAO;
import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.entity.UserStoryStaus;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsStoryDAOImpl implements TmsStoryDAO {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private TmsSprintDAO tmsSprintDAO;

	@Override
	public long createStory(StoryDTO storyDTO) {
		TmsUsers user = hibernateUtil.findByPropertyName("userName", storyDTO.getUser(), TmsUsers.class);
		TmsModule module = hibernateUtil.findByPropertyName("moduleName", storyDTO.getModule(), TmsModule.class);
		TmsStatusMst status = hibernateUtil.findByPropertyName("status", storyDTO.getStatus(), TmsStatusMst.class);
		TmsStoryMst storyMst = new TmsStoryMst();
		BeanUtils.copyProperties(storyDTO, storyMst);
		storyMst.setTmsModule(module);
		return (Long)hibernateUtil.create(storyMst);
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
                    .add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())).list();
			if(storyIds.size() > 0) {
				List<TmsStoryMst> stories = hibernateUtil.getCurrentSession().createCriteria(TmsStoryMst.class)
						  				  .add(Restrictions.in("storyId", storyIds)).list();
				
				return parseStories(stories);
			} else {
				return null;
			}
			
		} else {
			throw new Exception("Sprint not found");
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
			
			List<LinkedHashMap<String, Object>> ussList = new ArrayList<LinkedHashMap<String, Object>>();
			for (UserStoryStaus userStoryStaus : tmsStoryMst.getUserStoryStauses()) {
				LinkedHashMap<String, Object> uss = new LinkedHashMap<String, Object>();
				uss.put("id", userStoryStaus.getId());
				uss.put("createdDate", userStoryStaus.getCreatedDate());
				uss.put("assignedDate", userStoryStaus.getAssignedDate());
				uss.put("type", userStoryStaus.getType());
				uss.put("modifiedDate", userStoryStaus.getModifiedDate());
				uss.put("status", userStoryStaus.getTmsStatusMst().getStatus());
				uss.put("assignedTo", userStoryStaus.getTmsUsersByAssignedTo().getUserName());
				uss.put("modifiedBy", userStoryStaus.getTmsUsersByModifiedBy().getUserName());
				ussList.add(uss);
			}
			map.put("userStoryStatus", ussList);
			userStories.add(map);
		}
		return userStories;
	}
}
