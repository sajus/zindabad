package com.cyb.tms.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsProjectDAO;
import com.cyb.tms.entity.TmsProject;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsProjectDAOImpl implements TmsProjectDAO {
	
	@Autowired
    private HibernateUtil hibernateUtil;

	@Override
	public long createProject(TmsProject project) {
		return (long) hibernateUtil.create(project);
	}

	@Override
	public TmsProject getProject(Long pid) {
		return hibernateUtil.fetchById(pid, TmsProject.class);
	}

	@Override
	public List<TmsProject> getAllProjects() {
		return hibernateUtil.fetchAll(TmsProject.class);
	}

}
