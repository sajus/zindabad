package com.cyb.tms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsLeaveDAO;
import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dto.TmsSprintDTO;
import com.cyb.tms.entity.TmsLeaveMst;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.service.TmsSprintService;

@Service
@Transactional
public class TmsSprintServiceImpl implements TmsSprintService{
	
	public TmsSprintServiceImpl(){
		System.out.println("TmsSprintServiceImpl");
	}
	
	@Autowired 
	private TmsSprintDAO sprintDAO;
	
	@Override
	public long createSprint(TmsSprintDTO tmsSprintDTO) {
		return sprintDAO.createSprint(tmsSprintDTO);
	}

	@Override
	public List<String> updateSprint(TmsSprintDTO tmsSprintDTO) {
		return sprintDAO.updateSprint(tmsSprintDTO);
	}

	@Override
	public List<TmsSprintMst> getAllSprints(Long projectId) {
		return sprintDAO.getAllSprints(projectId);
	}

	@Override
	public TmsSprintMst getSprint(long id) {
		return sprintDAO.getSprint(id);
	}

	@Override
	public void updateSprintHours(Long projectId) {
		sprintDAO.updateSprintHours(projectId);
		
	}
	
	//@Override
	//public List<TmsSprintMst> getActiveSprint(Long projectId) throws Exception {
		//return (List<TmsSprintMst>) sprintDAO.getActiveSprint(projectId);
	//}
}
