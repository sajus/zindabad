package com.cyb.tms.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsLeaveDAO;
import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dao.TmsStoryDAO;
import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.dto.TmsSprintDTO;
import com.cyb.tms.entity.TmsLeaveMst;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsProject;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.service.TmsUserService;
import com.cyb.tms.util.HibernateUtil;
import com.cyb.tms.util.WorkingDaysCalculator;

@Repository
public class TmsSprintDAOImpl implements TmsSprintDAO {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private TmsUserService tmsUserService;
	
	@Autowired
	private TmsStoryDAO tmsStoryDAO;
	
	@Autowired
	private TmsLeaveDAO tmsLeaveDAO;
	
	@Value("${tms.workinghours}")
	private String workingHours;
	
	@Value("${tms.sprint.closed}")
	private String sprint_closed;
	
	@Value("${tms.sprint.open}")
	private String sprint_open;

	// -------------------Create a Sprint---------------
	@Override
	public long createSprint(TmsSprintDTO tmsSprintDTO){
		TmsProject project = hibernateUtil.fetchById(tmsSprintDTO.getProjectId(), TmsProject.class); 
		TmsSprintMst tmsSprintMst = new TmsSprintMst();
		BeanUtils.copyProperties(tmsSprintDTO, tmsSprintMst);
		tmsSprintMst.setTmsProject(project);
		tmsSprintMst.setSprintStatus(sprint_open);
		tmsSprintMst.setSprintHours(getSprintHours(tmsSprintDTO));
		tmsSprintMst.setSprintVelocity(getAverageSprintVelocity(tmsSprintDTO.getProjectId()));
		return (Long)hibernateUtil.create(tmsSprintMst);
	}

	//------------------- Update a Sprint --------------------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public List<String> updateSprint(TmsSprintDTO tmsSprintDTO) {
		TmsSprintMst tmsSprintMst = hibernateUtil.fetchById(tmsSprintDTO.getProjectId(), TmsSprintMst.class);
		tmsSprintMst.setSprintHours(getSprintHours(tmsSprintDTO));
		if (tmsSprintDTO.getSprintStatus().equalsIgnoreCase(sprint_closed)) {
			List<String> pendingStories = getIncompleteStroies(tmsSprintDTO.getProjectId());
			if (pendingStories.size() > 0) {
				return pendingStories;
			}
		}
		hibernateUtil.update(tmsSprintMst);
		return null;
	}

	private List<String> getIncompleteStroies(Long projectId) {
		return tmsStoryDAO.getIncompleteStoriesInSprint(projectId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TmsSprintMst> getAllSprints(long projectId) {
		List<TmsSprintMst> sprint = hibernateUtil.getCurrentSession().createCriteria(TmsSprintMst.class, "sp")
				.createAlias("tmsProject", "proj")
				.add(Restrictions.eq("proj.pid", projectId)).list();
		return sprint;
	}

	@Override
	public TmsSprintMst getSprint(long id) {
		return hibernateUtil.fetchById(id, TmsSprintMst.class);
	}

	@Override
	public TmsSprintMst getActiveSprint(long projectId) {
		TmsSprintMst sprint = (TmsSprintMst) hibernateUtil.getCurrentSession().createCriteria(TmsSprintMst.class, "sp")
				.createAlias("tmsProject", "proj")
				.add(Restrictions.eq("proj.pid", projectId))
				.add(Restrictions.eq("sp.sprintStatus", "OPEN")).uniqueResult();
		return sprint;
	}
	
	private int getAverageSprintVelocity(Long projectId) {
		Double sprintVelocity = (Double) hibernateUtil.getCurrentSession().createCriteria(TmsSprintMst.class, "sp")
				.createAlias("tmsProject", "proj")
				.setProjection(Projections.avg("sp.sprintVelocity"))
				.add(Restrictions.eq("proj.pid", projectId)).uniqueResult();
		return sprintVelocity.intValue();
	}

	private int getSprintHours(TmsSprintDTO tmsSprintDTO) {
		List<TmsUsers> users = tmsUserService.getUsersByStatus(tmsSprintDTO.getProjectId());
		int sprintDays = WorkingDaysCalculator.getWorkingDaysBetweenTwoDates(tmsSprintDTO.getSprintStartDate(), tmsSprintDTO.getSprintEndDate());
		int leaves = tmsLeaveDAO.getTotalLeavesBySprint(tmsSprintDTO.getProjectId());
		return (sprintDays * users.size() * Integer.parseInt(workingHours))-leaves;
	}


}
