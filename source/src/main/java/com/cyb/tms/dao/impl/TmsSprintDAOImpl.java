package com.cyb.tms.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsSprintDAOImpl implements TmsSprintDAO {

	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Override
	public long createSprint(TmsSprintMst sprint) {
		return (Long) hibernateUtil.create(sprint);
	}

	@Override
	public TmsSprintMst updateSprint(TmsSprintMst sprint) {
		return hibernateUtil.update(sprint);
	}

	@Override
	public List<TmsSprintMst> getAllSprints() {
		return hibernateUtil.fetchAll(TmsSprintMst.class);
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
