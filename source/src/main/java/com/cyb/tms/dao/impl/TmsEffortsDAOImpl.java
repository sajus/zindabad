package com.cyb.tms.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsEffortsDAO;
import com.cyb.tms.entity.TmsEfforts;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsEffortsDAOImpl implements TmsEffortsDAO {
	
	
	@Autowired
	private HibernateUtil hibernateUtil;

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

}
