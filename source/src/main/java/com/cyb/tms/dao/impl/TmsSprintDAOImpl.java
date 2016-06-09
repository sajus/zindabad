package com.cyb.tms.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dto.StoryDTO;
import com.cyb.tms.dto.TmsSprintDTO;
import com.cyb.tms.entity.TmsModule;
import com.cyb.tms.entity.TmsProject;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsStatusMst;
import com.cyb.tms.entity.TmsUsers;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsSprintDAOImpl implements TmsSprintDAO {

	@Autowired
	private HibernateUtil hibernateUtil;

	@Override
	public long createSprint(TmsSprintDTO tmsSprintDTO) {
		TmsProject projectId = hibernateUtil.fetchById(tmsSprintDTO.getProjectId(), TmsProject.class); 
		TmsSprintMst tmsSprintMst = new TmsSprintMst();
		BeanUtils.copyProperties(tmsSprintDTO, tmsSprintMst);
		tmsSprintMst.setTmsProject(projectId);
		return (Long)hibernateUtil.create(tmsSprintMst);
	}

	@Override
	public TmsSprintMst updateSprint(TmsSprintDTO tmsSprintDTO) {
		TmsProject projectId = hibernateUtil.fetchById(tmsSprintDTO.getProjectId(), TmsProject.class); 
		TmsSprintMst tmsSprintMst = new TmsSprintMst();
		BeanUtils.copyProperties(tmsSprintDTO, tmsSprintMst);
		tmsSprintMst.setTmsProject(projectId);
		return hibernateUtil.update(tmsSprintMst);
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

}
