package com.cyb.tms.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsProjectDAO;
import com.cyb.tms.dto.TmsProjectDTO;
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

	@Override
	public TmsProjectDTO getProjectByUserId(Long userId) {
		TmsProject tmsProject = (TmsProject) hibernateUtil.getCurrentSession().createCriteria(TmsProject.class)
								.createAlias("tmsUsers", "user")
								.add(Restrictions.eq("user.id", userId)).uniqueResult();
							
		return setDoToDto(tmsProject);
	}

	private TmsProjectDTO setDoToDto(TmsProject tmsProject) {
		TmsProjectDTO tmsPorjectDto = new TmsProjectDTO();
		BeanUtils.copyProperties(tmsProject, tmsPorjectDto);
		return tmsPorjectDto;
	}

}
