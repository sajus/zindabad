package com.cyb.tms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsLeaveDAO;
import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dto.TmsLeaveDTO;
import com.cyb.tms.entity.TmsLeaveMst;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.util.HibernateUtil;
import com.cyb.tms.util.WorkingDaysCalculator;

@Repository
public class TmsLeaveDAOImpl implements TmsLeaveDAO{

	@Autowired
    private HibernateUtil hibernateUtil;

	@Autowired
	private TmsSprintDAO tmsSprintDAO;
	
	@Override
	public long createLeave(TmsLeaveDTO tmsleaveDTO) {
     	return (long) hibernateUtil.create(setDtoToDo(tmsleaveDTO));
	}

	@Override
	public TmsLeaveMst updateLeave(TmsLeaveDTO tmsleaveDTO) {
		return hibernateUtil.update(setDtoToDo(tmsleaveDTO));
	}
	

	@Override
	public void deleteLeave(Long id) {
		TmsLeaveMst leave = hibernateUtil.fetchById(id, TmsLeaveMst.class);
		leave.setStatus("DELETED");
		hibernateUtil.update(leave);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TmsLeaveMst> getAllLeavesBySprint(Long projectId) throws Exception {
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		if(sprint != null) {
			Criteria criteria = hibernateUtil.getCurrentSession().createCriteria(TmsLeaveMst.class, "leave");
			criteria.createAlias("tmsSprintMst", "sp");
			criteria.add(Restrictions.eq("sp.sprintId", sprint.getSprintId()));
			return criteria.list();
			
		} else {
			throw new Exception("Sprint not found");
		}
	}

	@Override
	public int getTotalLeavesBySprint(Long projectId) {
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		if(sprint != null) {
			Long totalLeaves = (Long) hibernateUtil.getCurrentSession()
							.createCriteria(TmsLeaveMst.class, "leave")
							.createAlias("tmsSprintMst", "sp")
							.setProjection(Projections.sum("leave.duration"))
							.add(Restrictions.eq("sp.sprintId", sprint.getSprintId()))
							.uniqueResult();
			return totalLeaves.intValue();
		}
		return 0;
	}
	
	@Override
	public TmsLeaveMst getLeave(long id) {
		return hibernateUtil.fetchById(id, TmsLeaveMst.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TmsLeaveMst> getCurrentUserLeavesBySprint(Long userId, Long projectId) throws Exception {
		Object[] status = {"DELETED"};
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		if(sprint != null) {
			Criteria criteria = hibernateUtil.getCurrentSession().createCriteria(TmsLeaveMst.class, "leave");
			criteria.createAlias("tmsUsers", "user");
			criteria.createAlias("tmsSprintMst", "sp");
			criteria.add(Restrictions.eq("user.id", userId));
			criteria.add(Restrictions.not(Restrictions.in("leave.status", status)));
			criteria.add(Restrictions.eq("sp.sprintId", sprint.getSprintId()));
			return criteria.list();
			
		} else {
			throw new Exception("Sprint not found");
		}
	}
	
	/**
	 * @param tmsleaveDTO
	 * @return
	 */
	private TmsLeaveMst setDtoToDo(TmsLeaveDTO tmsleaveDTO) {
		TmsUsers user = hibernateUtil.fetchById( tmsleaveDTO.getUserId(), TmsUsers.class);
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(tmsleaveDTO.getProjectId());
		TmsLeaveMst leave =new TmsLeaveMst();
		BeanUtils.copyProperties(tmsleaveDTO, leave);
		leave.setTmsSprintMst(sprint);
		leave.setTmsUsers(user);
		leave.setStatus("EXISTS");
		leave.setDuration(WorkingDaysCalculator.getWorkingDaysBetweenTwoDates(leave.getStartDate(),leave.getEndDate()));
		return leave;
	}

	@Override
	public int calculateUserLeavesTotalBySprint(Long userId, Long sprintId) {
		Object[] status = {"DELETED"};
		Long totalLeaves = (Long) hibernateUtil.getCurrentSession()
				.createCriteria(TmsLeaveMst.class, "leave")
				.createAlias("tmsUsers", "users")
				.createAlias("tmsSprintMst", "sp")
				.setProjection(Projections.sum("leave.duration"))
				.add(Restrictions.eq("users.id", userId))
				.add(Restrictions.eq("sp.sprintId", sprintId))
				.add(Restrictions.not(Restrictions.in("leave.status", status)))
				.uniqueResult();
		return (totalLeaves != null) ? totalLeaves.intValue() : 0;
	}
}
