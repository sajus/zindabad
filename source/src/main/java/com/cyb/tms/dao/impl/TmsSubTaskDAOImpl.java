package com.cyb.tms.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsSubTaskDAO;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsSubTaskDAOImpl implements TmsSubTaskDAO {
	
	@Autowired
	private HibernateUtil hibernateUtil;

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

}
