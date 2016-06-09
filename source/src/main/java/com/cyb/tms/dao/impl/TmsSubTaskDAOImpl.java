package com.cyb.tms.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dao.TmsSubTaskDAO;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.entity.UserStoryStaus;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsSubTaskDAOImpl implements TmsSubTaskDAO {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private TmsSprintDAO tmsSprintDAO;

	@Override
	public long createSubtask(TmsSubtask subtask) {
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
                    .add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())).list();
			if(subtaskIds.size() > 0) {
				hibernateUtil.getCurrentSession().enableFilter(TmsStoryMst.LATEST_STATUS_FILTER);
				List<TmsSubtask> subtasks = hibernateUtil.getCurrentSession().createCriteria(TmsSubtask.class)
						  				  .add(Restrictions.in("subtaskId", subtaskIds)).list();
				hibernateUtil.getCurrentSession().disableFilter(TmsStoryMst.LATEST_STATUS_FILTER);
				
				return parseStories(subtasks);
			} else {
				return null;
			}
			
		} else {
			throw new Exception("Sprint not found");
		}
	}

//	@Override
//	public List<TmsSubtask> getSubTaskBySprintByUserwise(Long userId) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
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
				uss.put("assignedDate", userStoryStaus.getAssignedDate());
				uss.put("type", userStoryStaus.getType());
				uss.put("modifiedDate", userStoryStaus.getModifiedDate());
				uss.put("status", userStoryStaus.getTmsStatusMst().getStatus());
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
