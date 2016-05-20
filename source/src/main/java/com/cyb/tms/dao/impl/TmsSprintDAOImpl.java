package com.cyb.tms.dao.impl;

import java.util.List;

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
	public List<TmsSprintMst> getAllSprint() {
		return hibernateUtil.fetchAll(TmsSprintMst.class);
	}

	@Override
	public TmsSprintMst getSprint(long id) {
		return hibernateUtil.fetchById(id, TmsSprintMst.class);
	}

}
