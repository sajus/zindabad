package com.cyb.tms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsProjectDAO;
import com.cyb.tms.dto.TmsProjectDTO;
import com.cyb.tms.entity.TmsProject;
import com.cyb.tms.service.TmsProjectService;

@Transactional
@Service
public class TmsProjectServiceImpl implements TmsProjectService {
	
	@Autowired
	private TmsProjectDAO tmsProjectDAO;

	@Override
	public long createProject(TmsProject project) {
		return tmsProjectDAO.createProject(project);
	}

	@Override
	public TmsProject getProject(Long pid) {
		return tmsProjectDAO.getProject(pid);
	}

	@Override
	public List<TmsProject> getAllProjects() {
		return tmsProjectDAO.getAllProjects();
	}

	@Override
	public TmsProjectDTO getProjectByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
