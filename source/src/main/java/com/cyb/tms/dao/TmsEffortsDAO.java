package com.cyb.tms.dao;

import java.util.List;

import com.cyb.tms.entity.TmsEfforts;
import com.cyb.tms.entity.TmsSubtask;

public interface TmsEffortsDAO {
	
	public long createEffort(TmsEfforts effort);
	public TmsEfforts updateEffort(TmsEfforts effort);
	public List<TmsEfforts> getAllEfforts();
	public TmsEfforts getEffort(long id);

}
