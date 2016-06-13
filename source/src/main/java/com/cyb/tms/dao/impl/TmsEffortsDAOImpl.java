package com.cyb.tms.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsEffortsDAO;
import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.entity.TmsEfforts;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.entity.UserStoryStaus;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsEffortsDAOImpl implements TmsEffortsDAO {
	
	
	@Value("${tms.status.backlog}")
	private String backlog;
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private TmsSprintDAO tmsSprintDAO;

	@Override
	public long createEffort(TmsEfforts effort) {
		return (Long)hibernateUtil.create(effort);
	}

	@Override
	public TmsEfforts updateEffort(TmsEfforts effort) {
		return hibernateUtil.update(effort);
	}

	@Override
	public List<TmsEfforts> getAllEfforts() {
		return hibernateUtil.fetchAll(TmsEfforts.class);
	}

	@Override
	public TmsEfforts getEffort(long id) {
		return hibernateUtil.fetchById(id, TmsEfforts.class);
	}

	@Override
	public List<TmsEfforts> getCurrentUserEffortsBySprint(Long userId,
			Long projectId) {
		// TODO  need to implement filter
		List<TmsSubtask> subTasks = getCurrentUserSubtasksBySprint(userId, projectId);
		List<Long> subTaskIds = getSubtaskIds(subTasks);
		List<Double> efforts = getTotalEffortsBySubtask(projectId, subTasks);
		return null;
	}

	@SuppressWarnings("null")
	private List<Long> getSubtaskIds(List<TmsSubtask> subTasks) {
		List<Long> ids = null;
		for (TmsSubtask tmsSubtask : subTasks) {
			ids.add(tmsSubtask.getSubtaskId());
		}
		return ids;
	}

	@SuppressWarnings("unchecked")
	private List<Double> getTotalEffortsBySubtask(Long projectId, List<TmsSubtask> subTasks) {
		
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		List<Double> efforts = null;
		if(sprint != null) {
			efforts = hibernateUtil.getCurrentSession().createCriteria(TmsEfforts.class, "ef")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsSubtask", "sub")
					.setProjection( Projections.sum("ef.loggedHours"))
					.add(Restrictions.in("sub.subtaskId", subTasks))
					.add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())).list();
		}
		return efforts;
	}

	@SuppressWarnings("unchecked")
	private List<TmsSubtask> getCurrentUserSubtasksBySprint(Long userId, Long projectId) {
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		List<TmsSubtask> tmsSubtask = null;
		if(sprint != null) {
			tmsSubtask = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsSubtask", "sub")
					.createAlias("tmsUsersByAssignedTo", "users")
					.setProjection( Projections.distinct(Projections.property("tmsSubtask")))
					.add(Subqueries.propertyNotIn("sub.subtaskId",  DetachedCriteria.forClass(UserStoryStaus.class)
							.createAlias("tmsStatusMst", "tsm")
							.createAlias("tmsSubtask", "sub")
							.add(Restrictions.eq("tsm.status", backlog))
							.setProjection(Property.forName("sub.subtaskId"))))
					.add(Restrictions.eq("users.id", userId))
					.add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())).list();
		}
		return tmsSubtask;
	}
}
