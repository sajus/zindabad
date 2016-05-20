package com.cyb.tms.service;

import java.util.List;

import com.cyb.tms.entity.TmsSprintMst;

public interface TmsSprintService {
	
	public long createSprint(TmsSprintMst sprint);
	public TmsSprintMst updateSprint(TmsSprintMst sprint);
	public List<TmsSprintMst> getAllSprint();
	public TmsSprintMst getSprint(long id);

}
