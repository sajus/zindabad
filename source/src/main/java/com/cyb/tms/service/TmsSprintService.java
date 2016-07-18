package com.cyb.tms.service;

import java.util.List;

import com.cyb.tms.dto.TmsSprintDTO;
import com.cyb.tms.entity.TmsSprintMst;

public interface TmsSprintService {
	
	public long createSprint(TmsSprintDTO tmsSprintDTO);
	public List<String> updateSprint(TmsSprintDTO tmsSprintDTO);
	public List<TmsSprintMst> getAllSprints(Long projectId) throws Exception;
	public TmsSprintMst getSprint(long id);
	public void updateSprintHours(Long projectId);
	
}
