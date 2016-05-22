package com.cyb.tms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.tms.dao.TmsSprintDAO;
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
	public long createSprint(TmsSprintMst sprint) {
		return sprintDAO.createSprint(sprint);
	}

	@Override
	public TmsSprintMst updateSprint(TmsSprintMst sprint) {
		return sprintDAO.updateSprint(sprint);
	}

	@Override
	public List<TmsSprintMst> getAllSprints() {
		return sprintDAO.getAllSprints();
	}

	@Override
	public TmsSprintMst getSprint(long id) {
		return sprintDAO.getSprint(id);
	}
}
