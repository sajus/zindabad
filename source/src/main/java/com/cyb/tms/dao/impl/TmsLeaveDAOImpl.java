package com.cyb.tms.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsLeaveDAO;
import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dto.TmsLeaveDTO;
import com.cyb.tms.entity.TmsLeaveMst;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.entity.TmsStoryMst;
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
	
//	leave.setTmsSprintMst();
//	leave.setTmsUsers();
 //  
		
		TmsUsers id = hibernateUtil.fetchById( tmsleaveDTO.getId(), TmsUsers.class);
	//	TmsSprintMst sprintId = hibernateUtil.fetchById(tmsleaveDTO.getSprintId(), TmsSprintMst.class);
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(tmsleaveDTO.getProjectId());
		TmsLeaveMst leave =new TmsLeaveMst();
		BeanUtils.copyProperties(tmsleaveDTO, leave);
		leave.setTmsSprintMst(sprint);
		leave.setTmsUsers(id);
		leave.setDuration(WorkingDaysCalculator.getWorkingDaysBetweenTwoDates(leave.getStartDate(),leave.getEndDate()));
     	return (long) hibernateUtil.create(leave);
		
		/*TmsStatusMst status = hibernateUtil.findByPropertyName("status", storyDTO.getStatus(), TmsStatusMst.class);
		TmsStoryMst storyMst = new TmsStoryMst();
		BeanUtils.copyProperties(storyDTO, storyMst);
		storyMst.setTmsModule(module);
		return (Long)hibernateUtil.create(storyMst);*/		
	}

	@Override
	public TmsLeaveMst updateLeave(TmsLeaveMst leave) {
		return hibernateUtil.update(leave);
	}
	

	@Override
	public void deleteLeave(long id) {
		TmsLeaveMst leave = new TmsLeaveMst();
		leave.setLeaveId(id);
		hibernateUtil.delete(leave);
		
	}

	@Override
	public List<TmsLeaveMst> getAllLeaves() {
		return hibernateUtil.fetchAll(TmsLeaveMst.class);
	}

	@Override
	public TmsLeaveMst getLeave(long id) {
		return hibernateUtil.fetchById(id, TmsLeaveMst.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TmsLeaveMst> getLeaveBySprint(Long projectId) throws Exception {
		
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

	/*Criteria criteria = hibernateUtil.getCurrentSession().createCriteria(TmsLeaveMst.class, "leave");
	criteria.createAlias("tmsSprintMst", "sp");
	criteria.createAlias("tmsUsers", "user");
	criteria.add(Restrictions.eq("sp.sprintId", sprint.getSprintId()));
	criteria.add(Restrictions.eq("user.id", userId));
	return criteria.list();*/
	
	
}
