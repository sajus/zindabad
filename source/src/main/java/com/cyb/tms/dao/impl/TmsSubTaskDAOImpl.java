package com.cyb.tms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dao.TmsSubTaskDAO;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsStoryMst;
import com.cyb.tms.entity.TmsSubtask;
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
	public List<TmsSubtask> getSubTaskBySprint(Long projectId) throws Exception {
		
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		if(sprint != null) {
			Criteria criteria = hibernateUtil.getCurrentSession().createCriteria(TmsSubtask.class, "subtask");
			criteria.createAlias("userStoryStatus", "uss");
			criteria.createAlias("tmsSprintMst", "sp");
			criteria.add(Restrictions.eqProperty("uss.jiraId", "story.jiraId"));
			criteria.add(Restrictions.eqProperty("uss.tmsSprintMst", "sp.sprintId"));
			criteria.add(Restrictions.eq("sp.sprintId", sprint.getSprintId()));
			return criteria.list();
			
		} else {
			throw new Exception("Sprint not found");
		}
	}

	@Override
	public List<TmsSubtask> getSubTaskBySprintByUserwise(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
