package com.cyb.tms.dao;

import java.util.List;

import com.cyb.tms.dto.TmsProjectDTO;
import com.cyb.tms.entity.TmsProject;

public interface TmsProjectDAO {

	public long createProject(TmsProject project);
	public TmsProject getProject(Long  pid);
	public List<TmsProject> getAllProjects();
	public TmsProjectDTO getProjectByUserId(Long userId);
}
