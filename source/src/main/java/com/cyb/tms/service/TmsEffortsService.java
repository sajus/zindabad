package com.cyb.tms.service;

import java.util.List;

import com.cyb.tms.entity.TmsEfforts;

public interface TmsEffortsService {
	
	public long createEffort(TmsEfforts effort);
	public TmsEfforts updateEffort(TmsEfforts effort);
	public List<TmsEfforts> getAllEfforts();
	public TmsEfforts getEffort(long id);
	public List<TmsEfforts> getCurrentUserEffortsBySprint(Long userId,
			Long projectId);


}
