package com.cyb.tms.dao;

import java.util.List;

import com.cyb.tms.dto.TmsSprintDTO;
import com.cyb.tms.entity.TmsSprintMst;

public interface TmsSprintDAO {
	
	public long createSprint(TmsSprintDTO tmsSprintDTO);
	public List<String> updateSprint(TmsSprintDTO tmsSprintDTO);
	public List<TmsSprintMst> getAllSprints(long projectId);
	public TmsSprintMst getSprint(long id);
	public TmsSprintMst getActiveSprint(long projectId);
	
}
